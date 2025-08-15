package config;

import config.modules.CalendarConfig;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Config {
    private CalendarConfig calendar;

    private static String path = "settings.toml";

    public CalendarConfig getCalendar() {
        return calendar;
    }

    public static Config load() {
        TomlMapper tomlMapper = new TomlMapper();

        try {
            // Config.class: clase Config, como objeto de class. Para acceder a los metadatos de la clase
            // .getClassLoader(): obtiene el ClassLoader para el siguiente método
            // .getResource(): obtiene el archivo .toml, que es conocido solo por la clase
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
