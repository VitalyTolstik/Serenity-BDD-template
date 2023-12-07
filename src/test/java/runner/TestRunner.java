package runner;

import factory.ScreenTypeFactory;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        tags = "@1",
        features = "src/test/java/features",
        glue = {"steps", "hooks"},
        plugin = "pretty",
        objectFactory = ScreenTypeFactory.class
)
public class TestRunner {
}
