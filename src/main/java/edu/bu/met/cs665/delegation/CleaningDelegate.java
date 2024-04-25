package edu.bu.met.cs665.delegation;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/24/2024
 * File Name: CleaningDelegate.java
 * Description: This class is responsible for performing various data cleaning operations.
 * It provides methods to sanitize and prepare data for processing, ensuring consistency and reliability.
 */
public class CleaningDelegate {

    /**
     * Trims leading and trailing whitespace from the data.
     * @param data The data to clean.
     * @return The trimmed data.
     */
    public String trimWhitespace(String data) {
        return data.trim();
    }

    /**
     * Removes non-printable characters from the data.
     * @param data The data to clean.
     * @return The cleaned data with non-printable characters removed.
     */
    public String removeNonPrintableCharacters(String data) {
        return data.replaceAll("\\p{Cntrl}", "");
    }

    /**
     * Corrects common data entry errors such as replacing accidental double spaces or known misspellings.
     * @param data The data to clean.
     * @return The corrected data.
     */
    public String correctCommonErrors(String data) {
        return data.replace("  ", " ").replaceAll("teh", "the");
    }

    /**
     * Performs a full cleaning sequence on the data, combining all available cleaning methods.
     * @param data The data to clean.
     * @return The fully cleaned and processed data.
     */
    public String cleanData(String data) {
        data = trimWhitespace(data);
        data = removeNonPrintableCharacters(data);
        data = correctCommonErrors(data);
        return data;
    }
}
