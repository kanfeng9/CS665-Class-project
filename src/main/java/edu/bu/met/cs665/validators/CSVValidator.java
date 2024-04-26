package edu.bu.met.cs665.validators;

import java.util.Arrays;
import java.util.List;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: CSVValidator.java
 * Description: This class implements the Validator interface for CSV formatted data.
 * It checks for the presence of headers and consistent columns in each row.
 */
public class CSVValidator implements Validator {

    /**
     * Validates a CSV string to ensure that each row has the same number of columns as the header.
     *
     * @param data The CSV data as a String.
     * @return boolean indicating whether the CSV data is valid.
     */
    @Override
    public boolean validate(String data) {
        if (data == null || data.isEmpty()) {
            return false;
        }

        String[] lines = data.split("\\r?\\n");
        if (lines.length == 0) {
            return false;
        }

        // Get the number of columns in the header
        int headerColumns = lines[0].split(",").length;

        for (int i = 1; i < lines.length; i++) {
            int rowColumns = lines[i].split(",").length;
            if (rowColumns != headerColumns) {
                return false;  // Row does not have the same number of columns as the header
            }
        }
        return true;
    }
}
