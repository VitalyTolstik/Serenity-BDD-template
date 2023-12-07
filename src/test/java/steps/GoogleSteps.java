package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.hamcrest.MatcherAssert;
import pages.GooglePage;
import utils.EnvironmentSettingsUtils;

public class GoogleSteps {

    @Steps
    GooglePage googlePage;

    @Given("I opened url")
    public void openGoogleSite() {
        googlePage.openUrl(EnvironmentSettingsUtils.getUrl());
        googlePage.getDriver().manage().window().maximize();
        googlePage.clickAcceptAll();
    }

    @When("I type {string}")
    public void typeText(String text) {
        MatcherAssert.assertThat("Input field should be visible", googlePage.isInputFieldVisible());
        googlePage.typeToInput(text);
    }

    @And("I click search button")
    public void clickSearch() {
        googlePage.clickSearchButton();
    }

    @When("I click on first link")
    public void clickFirstLink() {
        googlePage.clickFirstLink();
    }
}
