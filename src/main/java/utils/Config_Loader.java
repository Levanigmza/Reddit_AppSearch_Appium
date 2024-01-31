package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config_Loader {
    private static final String CONFIG_FILE = "Android.properties";
    private Properties properties;

    public Config_Loader() {
        this.properties = loadProperties();
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                props.load(input);
            } else {
                System.out.println("Sorry, unable to find " + CONFIG_FILE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return props;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
