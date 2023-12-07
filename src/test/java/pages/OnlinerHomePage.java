package pages;

import factory.ScreenType;
import factory.ScreenTypes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

@ScreenType(name = ScreenTypes.IOS)
public abstract class OnlinerHomePage extends PageObject {

    protected abstract WebElementFacade navigationBar();

    @Step
    public boolean isNavigationBarVisible() {
        return navigationBar().isVisible();
    }
}
