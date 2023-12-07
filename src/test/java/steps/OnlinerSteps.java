package steps;

import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;
import org.hamcrest.MatcherAssert;
import pages.OnlinerHomePage;

public class OnlinerSteps {

    @Steps
    OnlinerHomePage onlinerHomePage;

    @Then("Onliner site is open")
    public void isOnlinerOpened() {
        MatcherAssert.assertThat("Home page should be visible", onlinerHomePage.isNavigationBarVisible());
    }
}
