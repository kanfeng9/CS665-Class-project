package edu.bu.met.cs665.transformers;

import edu.bu.met.cs665.validators.CSVValidator;
import edu.bu.met.cs665.validators.XMLValidator;
import edu.bu.met.cs665.validators.Validator;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: TransformerFactory.java
 * Description: This factory class is responsible for creating instances of various data transformers
 * and validators. It provides methods to get a transformer and a validator based on a string identifier,
 * facilitating the creation of the correct object according to the desired transformation type.
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
            case "1":
                return new CSVToJsonTransformer();
            case "2":
                return new XMLToYAMLTransformer();
            default:
                return null;
        }
    }

    /**
     * Retrieves a validator appropriate for the specified type of data.
     *
     * @param type The type of data for which the validator is needed, e.g., "CSVToJson" or "XMLToYAML".
     * @return Validator instance suitable for the data type, or null if no specific validation is needed.
     */
    public static Validator getValidator(String type) {
        switch (type) {
            case "CSVToJson":
                return new CSVValidator();
            case "XMLToYAML":
                return new XMLValidator();
            default:
                return null;
        }
    }
}
