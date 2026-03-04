package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialsLoader {


    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = CredentialsLoader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                System.out.println("Error: application.properties not found in resources!");
                return properties;
            }
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Error reading properties file!");
        }
        return properties;
    }
}