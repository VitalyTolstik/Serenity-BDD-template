package pages;

import factory.ScreenType;
import factory.ScreenTypes;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

@ScreenType(name = ScreenTypes.WEB)
public class GooglePageWeb extends GooglePage {
    @Override
    protected WebElementFacade inputField() {
        return $(By.name("q"));
    }

    @Override
    protected WebElementFacade searchButton() {
        return $(By.name("btnK"));
    }

    @Override
    protected WebElementFacade firstLink() {
        return $(By.xpath("(//*[@class='g']//h3)[1]"));
    }

    @Override
    protected WebElementFacade acceptAllButton() {
        return $(By.id("L2AGLb"));
    }
}
