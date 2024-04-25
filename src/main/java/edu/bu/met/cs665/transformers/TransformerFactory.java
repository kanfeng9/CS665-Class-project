package edu.bu.met.cs665.transformers;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: TransformerFactory.java
 * Description: This factory class is responsible for creating instances of various data transformers.
 * It provides a method to get a transformer based on a string identifier, facilitating the creation
 * of the correct transformer object according to the desired transformation type.
 */
public class TransformerFactory {

    /**
     * Creates a data transformer based on the specified type.
     * @param type The type of transformer to create, e.g., "CSVToJson" or "XMLToYAML".
     * @return DataTransformer instance that matches the type.
     */
    public static DataTransformer getTransformer(String type) {
        if (type == null) {
            return null;
        }
        switch (type) {
            case "CSVToJson":
                return new CSVToJsonTransformer();
            case "XMLToYAML":
                return new XMLToYAMLTransformer();
            default:
                return null;
        }
    }
}
