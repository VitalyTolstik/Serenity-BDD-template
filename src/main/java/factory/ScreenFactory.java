package factory;

import lombok.extern.java.Log;
import net.serenitybdd.core.pages.PageObject;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;
import utils.GlobalSettingsUtils;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Log
public class ScreenFactory {

    private ScreenFactory() {
    }

    public static ScreenFactory getInstance() {
        return new ScreenFactory();
    }

    public Class<?> getScreen(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        Reflections reflections = new Reflections(packageName);
        try {
            ScreenTypes pageType = GlobalSettingsUtils.getScreenType();
            Set<Class<?>> screens = new HashSet<>(reflections.getSubTypesOf(PageObject.class));
            return screens.stream()
                    .filter(value -> value.isAnnotationPresent(ScreenType.class))
                    .filter(value -> value.getAnnotation(ScreenType.class).name() == pageType)
                    .filter(clazz::isAssignableFrom)
                    .findFirst()
                    .orElse(screens.stream()
                            .filter(page -> page.getName().equalsIgnoreCase(clazz.getName()))
                            .findFirst()
                            .orElse(clazz));
        } catch (ReflectionsException | NoSuchElementException e) {
            log.warning("Could not find package \"%s\" with Screens. Please specify value \"screensLocation\" in settings file.".formatted(packageName));
            return clazz;
        }
    }
}
