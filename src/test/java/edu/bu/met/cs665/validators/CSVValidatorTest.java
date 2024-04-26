package edu.bu.met.cs665.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: CSVValidatorTest.java
 * Description: This class contains tests for the CSVValidator to ensure it correctly identifies
 * valid and invalid CSV data based on consistency in the number of columns across rows.
 */
public class CSVValidatorTest {

    @Test
    public void testValidate_validCSV_returnsTrue() {
        CSVValidator validator = new CSVValidator();
        String csvData = "name,age\nAlice,30\nBob,25";
        assertTrue(validator.validate(csvData), "Validator should return true for valid CSV data with consistent columns.");
    }

    @Test
    public void testValidate_invalidCSV_returnsFalse() {
        CSVValidator validator = new CSVValidator();
        String csvData = "name,age\nAlice,30\nBob";  // Missing an age for Bob
        assertFalse(validator.validate(csvData), "Validator should return false for CSV data with inconsistent columns.");
    }

    @Test
    public void testValidate_emptyCSV_returnsFalse() {
        CSVValidator validator = new CSVValidator();
        String csvData = "";
        assertFalse(validator.validate(csvData), "Validator should return false for an empty CSV string.");
    }

    @Test
    public void testValidate_CSVWithOnlyHeaders_returnsTrue() {
        CSVValidator validator = new CSVValidator();
        String csvData = "name,age";
        assertTrue(validator.validate(csvData), "Validator should return true for CSV data with only headers and no data rows.");
    }

    @Test
    public void testValidate_csvWithTrailingNewLines_returnsTrue() {
        CSVValidator validator = new CSVValidator();
        String csvData = "name,age\nAlice,30\nBob,25\n";
        assertTrue(validator.validate(csvData), "Validator should return true for CSV data with trailing new lines.");
    }
}
