package edu.bu.met.cs665.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/24/2024
 * File Name: ConfigLoader.java
 * Description: This utility class provides methods to load configuration settings from a properties file.
 * It simplifies the process of managing configurable options for the application.
 */
public class ConfigLoader {

    private Properties properties;

    /**
     * Loads configuration properties from the specified file path.
     * @param filePath The path to the properties file.
     * @throws IOException If an error occurs while reading the file.
     */
    public ConfigLoader(String filePath) throws IOException {
        properties = new Properties();
        loadProperties(filePath);
    }

    /**
     * Loads properties from the given file.
     * @param filePath Path to the properties file.
     * @throws IOException If the file cannot be found or read.
     */
    private void loadProperties(String filePath) throws IOException {
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        }
    }

    /**
     * Retrieves a property value by key.
     * @param key The key for the property.
     * @return The value of the property or null if the property is not found.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
