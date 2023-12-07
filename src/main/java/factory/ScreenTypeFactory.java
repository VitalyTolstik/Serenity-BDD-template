package factory;

import io.cucumber.core.backend.ObjectFactory;
import io.cucumber.core.exception.CucumberException;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import net.serenitybdd.core.lifecycle.LifecycleRegister;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.*;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import net.thucydides.model.domain.TestOutcome;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import java.lang.reflect.Constructor;
import java.util.*;

import static net.thucydides.model.ThucydidesSystemProperty.STEP_CREATION_STRATEGY;

public class ScreenTypeFactory implements ObjectFactory {

    private final Set<Class<?>> classes = Collections.synchronizedSet(new HashSet<>());

    private final Map<Class<?>, Object> instances = Collections.synchronizedMap(new HashMap<>());

    public void start() {
        //add some if needed
    }

    public void stop() {
        instances.clear();
        Serenity.done(false);
    }

    @Override
    public boolean addClass(Class<?> glueClass) {
        classes.add(glueClass);
        return true;
    }

    public <T> T getInstance(Class<T> type) {
        T instance = type.cast(instances.get(type));
        if (instance == null) {
            instance = cacheNewInstance(type);
        }
        return instance;
    }

    /**
     * Tries to instantiate the type using an empty constructor, if it does not work, tries to instantiate
     * using a constructor with a Pages parameter.
     */
    private <T> T cacheNewInstance(Class<T> type) {
        T instance = newInstance(type);
        instances.put(type, instance);
        return instance;
    }

    private <T> T newInstance(Class<T> type) {
        T instance;
        try {
            if (hasConstructorWithPagesParameter(type)) {
                instance = createNewPageEnabledStepCandidate(type);
            } else {
                Constructor<T> constructor = type.getConstructor();
                instance = constructor.newInstance();
                initializeSteps(instance);
            }
        } catch (ReflectiveOperationException e) {
            throw new CucumberException(String.format("Failed to instantiate %s - this class doesn't have an empty or a page enabled constructor\"", type), e);
        }
        Serenity.initializeWithNoStepListener(instance).throwExceptionsImmediately();

        TestOutcome newTestOutcome = StepEventBus.getEventBus().getBaseStepListener().getCurrentTestOutcome();
        LifecycleRegister.register(instance);
        LifecycleRegister.invokeMethodsAnnotatedBy(BeforeScenario.class, newTestOutcome);

        return instance;
    }

    private <T> T createNewPageEnabledStepCandidate(final Class<T> type) {
        T newInstance;
        try {
            ThucydidesWebDriverSupport.initialize();
            Pages pageFactory = ThucydidesWebDriverSupport.getPages();
            Class<?>[] constructorArgs = new Class[1];
            constructorArgs[0] = Pages.class;
            Constructor<T> constructor = type.getConstructor(constructorArgs);
            newInstance = constructor.newInstance(pageFactory);
            initializeSteps(newInstance);
            Serenity.initialize(newInstance);
        } catch (Exception e) {
            throw new CucumberException(String.format("%s doesn't have an empty or a page enabled constructor.", type), e);
        }
        return newInstance;
    }

    private boolean hasConstructorWithPagesParameter(Class<?> type) {
        Class<?>[] constructorArgs = new Class[1];
        constructorArgs[0] = Pages.class;
        try {
            type.getConstructor(constructorArgs);
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

    public <T> void initializeSteps(T object) {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.currentEnvironmentVariables();
        List<StepsAnnotatedField> stepFields = StepsAnnotatedField.findOptionalAnnotatedFields(object.getClass());
        for (StepsAnnotatedField stepField : stepFields) {
            ScreenFactory screenFactory = ScreenFactory.getInstance();
            Class<?> scenarioStepsClass = screenFactory.getScreen(stepField.getFieldClass());
            Object steps = getStepLibraryCreationStrategy(StepFactory.getFactory(), stepField, environmentVariables)
                    .initiateStepsFor(scenarioStepsClass);
            stepField.setValue(object, steps);
            stepField.assignActorNameIn(steps);
        }
    }

    private StepLibraryCreationStrategy getStepLibraryCreationStrategy(StepFactory stepFactory,
                                                                       StepsAnnotatedField stepsField,
                                                                       EnvironmentVariables environmentVariables) {
        boolean useDefaultStrategy = STEP_CREATION_STRATEGY.from(environmentVariables, "default")
                .equalsIgnoreCase("default");

        return (useDefaultStrategy) ?
                new IndividualInstancesByDefaultStepCreationStrategy(stepFactory, stepsField) :
                new SharedInstancesByDefaultStepCreationStrategy(stepFactory, stepsField);
    }
}
