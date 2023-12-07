package pages;

import factory.ScreenType;
import factory.ScreenTypes;
import net.serenitybdd.core.pages.WebElementFacade;

@ScreenType(name = ScreenTypes.IOS)
public class GooglePageIOS extends GooglePage {
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
