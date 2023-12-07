package pages;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public abstract class GooglePage extends PageObject {

    //    @FindBy(name = "q")
    protected abstract WebElementFacade inputField();
    //    @FindBy(name = "btnK")
    protected abstract WebElementFacade searchButton();
    //    @FindBy(xpath = "(//*[@class='g']//h3)[1]")
    protected abstract WebElementFacade firstLink();
    //    @FindBy(id = "L2AGLb")
    protected abstract WebElementFacade acceptAllButton();

    @Step
    public boolean isInputFieldVisible() {
        return inputField().isVisible();
    }

    @Step
    public void typeToInput(String text) {
        inputField().sendKeys(text);
    }

    @Step
    public void clickSearchButton() {
        searchButton().click();
    }

    @Step
    public void clickFirstLink() {
        firstLink().click();
    }

    @Step
    public void clickAcceptAll() {
        acceptAllButton().click();
    }
}
