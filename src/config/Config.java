package config;

import config.modules.CalendarConfig;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Config {
    private CalendarConfig calendar;

    private static String path = "settings/settings.toml";

    public CalendarConfig getCalendar() {
        return calendar;
    }

    public static Config load() {
        TomlMapper tomlMapper = new TomlMapper();

        try {
            URL resource = Config.class.getClassLoader().getResource(path);
            if (resource == null) {
                throw new RuntimeException("Resource not found: " + path);
            }
            return tomlMapper.readValue(new File(resource.getFile()), Config.class);
        }
        catch (IOException e) {
            throw new RuntimeException("Couldn't load the configuration", e);
        }
    }
}
