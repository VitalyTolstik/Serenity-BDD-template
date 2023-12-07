package utils;

import factory.ScreenTypes;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

@UtilityClass
public class GlobalSettingsUtils {
    private static final String CONFIG_NAME = "config.yml";
    private static final String SCREEN_TYPE = "screen.type";
    private static final String ENVIRONMENT_NAME = "environment.name";

    private static Config getConfiguration() {
        try (InputStream resourceAsStream = GlobalSettingsUtils.class.getClassLoader().getResourceAsStream(CONFIG_NAME)) {
            return new Yaml().loadAs(resourceAsStream, Config.class);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to read the configuration file", exception);
        }
    }

    private static String getNameScreenType() {
        return System.getProperty(SCREEN_TYPE) != null ? System.getProperty(SCREEN_TYPE) : getConfiguration().getScreen().getType();
    }

    public static String getEnvironmentName() {
        return System.getProperty(ENVIRONMENT_NAME) != null ? System.getProperty(ENVIRONMENT_NAME) : getConfiguration().getEnvironment().getName();
    }

    public boolean isIOs() {
        return ScreenTypes.IOS.name().equalsIgnoreCase(getNameScreenType());
    }

    public boolean isAndroid() {
        return ScreenTypes.ANDROID.name().equalsIgnoreCase(getNameScreenType());
    }

    public boolean isWeb() {
        return ScreenTypes.WEB.name().equalsIgnoreCase(getNameScreenType());
    }

    public ScreenTypes getScreenType() {
        if (isIOs()) return ScreenTypes.IOS;
        if (isAndroid()) return ScreenTypes.ANDROID;
        if (isWeb()) return ScreenTypes.WEB;
        throw new RuntimeException("%s screen type is not supported".formatted(getNameScreenType()));
    }

    @Data
    public static class Config {
        private Environment environment;
        private Screen screen;
    }

    @Data
    public static class Environment {
        private String name;
    }

    @Data
    public static class Screen {
        private String type;
    }
}
