package BBDD;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigLoader {
    private static Properties props;

    public static Properties get() {
        if (props == null) {
            props = new Properties();
            try (FileInputStream fis = new FileInputStream("config/variable_resolution.properties")) {
                props.load(fis);
            } catch (IOException ex) {
                Logger.getLogger(ConfigLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return props;
    }
}