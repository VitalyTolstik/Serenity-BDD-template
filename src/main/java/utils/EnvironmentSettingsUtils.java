package utils;

import lombok.Data;
import lombok.experimental.UtilityClass;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@UtilityClass
public class EnvironmentSettingsUtils {

    private static final String CONFIG_PATH = "src/main/resources/environment/%s/config.yml";

    private static Config getConfiguration() {
        try {
            String yamlContent = Files.readString(Path.of(CONFIG_PATH.formatted(GlobalSettingsUtils.getEnvironmentName())));
            return new Yaml().loadAs(yamlContent, Config.class);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to read the configuration file");
        }
    }

    public static String getUrl() {
        return getConfiguration().getUrl();
    }

    @Data
    public static class Config {
        private String url;
    }
}
