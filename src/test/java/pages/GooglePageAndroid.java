package pages;

import factory.ScreenType;
import factory.ScreenTypes;
import net.serenitybdd.core.pages.WebElementFacade;

@ScreenType(name = ScreenTypes.ANDROID)
public class GooglePageAndroid extends GooglePage {
    @Override
    protected WebElementFacade inputField() {
        return null;
    }

    @Override
    protected WebElementFacade searchButton() {
        return null;
    }

    @Override
    protected WebElementFacade firstLink() {
        return null;
    }

    @Override
    protected WebElementFacade acceptAllButton() {
        return null;
    }
}
