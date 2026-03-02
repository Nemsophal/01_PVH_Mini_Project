package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CredentialsLoader {


    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader("application.properties"))
        ) {
            properties.load(bufferedReader);
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return properties;
    }
}