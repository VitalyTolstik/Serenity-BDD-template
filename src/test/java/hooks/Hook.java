package hooks;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import net.serenitybdd.core.Serenity;
import net.thucydides.model.util.EnvironmentVariables;
import utils.GlobalSettingsUtils;

public class Hook {

    @Before
    public static void bsForMvn() {
    }

    @Before
    public static void configInit() {
        Config load = ConfigFactory.load("serenity.conf");
        //TODO check it
//        GlobalSettingsUtils.getScreenType();
    }
}
