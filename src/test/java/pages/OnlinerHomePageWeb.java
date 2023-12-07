package pages;

import factory.ScreenType;
import factory.ScreenTypes;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

@ScreenType(name = ScreenTypes.WEB)
public class OnlinerHomePageWeb extends OnlinerHomePage {
    @Override
    protected WebElementFacade navigationBar() {
        return $(By.className("b-main-navigation"));
    }
}
