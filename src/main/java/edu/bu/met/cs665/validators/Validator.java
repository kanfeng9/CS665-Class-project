package edu.bu.met.cs665.validators;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: Validator.java
 * Description: This interface defines the basic structure for validators that check the correctness
 * and integrity of input data before processing. Implementations of this interface can perform
 * format-specific validations.
 */
public interface Validator {
    /**
     * Validates the input data and returns true if it meets the required standards.
     *
     * @param data The data to validate as a String.
     * @return boolean indicating whether the data is valid or not.
     */
    boolean validate(String data);
}
