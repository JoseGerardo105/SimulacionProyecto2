package application.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class ConfigManager {

    private ConfigManager() {
        prop = new Properties();
    }

    private static final String FILE = "config/config.properties";

    private final Properties prop;

    /**
     * Carga las propiedades de esta url.
     */
    public void load() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(FILE)) {
            prop.load(input);
        } catch (IOException ex) {
            throw new IOException("Chargement configuration impossible ",ex);
        }
    }

    /**
     * Devuelve el valor del nombre de la clave.
     */
    public String getProperties(String name) {
        return prop.getProperty(name);
    }

    /**
     * Devuelve la instancia única
     */
    public static ConfigManager getInstance() {
        return ConfigManagerHolder.INSTANCE;
    }

    private static class ConfigManagerHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }
}
