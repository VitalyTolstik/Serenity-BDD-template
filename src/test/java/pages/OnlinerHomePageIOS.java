package pages;

import factory.ScreenType;
import factory.ScreenTypes;
import net.serenitybdd.core.pages.WebElementFacade;

@ScreenType(name = ScreenTypes.IOS)
public class OnlinerHomePageIOS extends OnlinerHomePage {
    @Override
    protected WebElementFacade navigationBar() {
        return null;
    }
}
