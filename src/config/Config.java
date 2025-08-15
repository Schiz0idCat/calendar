package config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static final Map<String, String> settings = new HashMap<>();

    static {
        loadConfig("src/config/setting.toml");
    }

    // esto es un parser de TOML
    private static void loadConfig(String path) {
        String currentSection = ""; // almacena la sección actual del TOML (los [])

        try {
            for (String line: Files.readAllLines(Path.of(path))) {
                line = line.strip(); // como .trim() pero para unicode

                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // se salta lineas en blanco o comentarios
                }
                if (line.startsWith("[") && line.endsWith("]")) {
                    currentSection = line.substring(1, line.length() - 1).trim(); // recolecta el string interior
                }
                else if (line.contains("=")) {
                    String[] parts = line.split("=", 2); // el 2 indica el número de splits máxmimo que hará
                    String key = parts[0].trim();
                    String value = parts[1].trim().split("#", 2)[0].replaceAll("^\"|\"$", ""); // quita las ""

                    // Explicación de la regex:
                    // para todo \" -> esto solo escapa el ", para no cortar el string
                    // ^\"
                    //     ^ -> inicio de la cadena
                    //     " -> lo buscado
                    //  por lo tanto: una " al inicio de la cadena
                    // | -> or
                    // \"$
                    //     " -> lo buscado
                    //     $ -> al final de la cadena
                    // por lo tanto: una " al final de la cadena
                    //
                    // Lo que la regex hace:
                    // si hay un " al inicio o al final de la cadena, lo reemplaza por nada (las borra)

                    settings.put(currentSection + "." + key, value);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Couldn't read the configuration file", e);
        }
    }

    public static String get(String key) {
        return settings.get(key);
    }
}
