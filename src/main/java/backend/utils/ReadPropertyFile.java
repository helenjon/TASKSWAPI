package backend.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

    private static Properties properties;

    public static Properties getProperties(String propertiesFilePath) {
        try {
            properties = new Properties();
            FileInputStream fileInput = new FileInputStream(propertiesFilePath);
            properties.load(fileInput);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;

    }
}
