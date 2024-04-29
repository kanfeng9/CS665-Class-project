package edu.bu.met.cs665.transformers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: CSVToJsonTransformer.java
 * Description: This class implements the DataTransformer interface to convert CSV data into JSON format.
 * It reads CSV from a file, processes it using simple parsing techniques, and produces a JSON output.
 */
public class CSVToJsonTransformer implements DataTransformer {

    @Override
    public void transform(String inputFilePath, String outputFilePath) throws IOException {
        String csvContent = new String(Files.readAllBytes(Paths.get(inputFilePath)));
        try {
            String json = convertCSVToJson(csvContent);

            if (outputFilePath != null) {
                Files.write(Paths.get(outputFilePath), json.getBytes());
            } else {
                System.out.println(json);
            }
        } catch (IllegalArgumentException e) {
            throw new IOException("Error processing CSV to JSON transformation: " + e.getMessage(), e);
        }
    }

    /**
     * Converts CSV data to JSON format.
     * Assumes the CSV file has a header row.
     *
     * @param csvData String representation of the input CSV data.
     * @return String representation of the JSON formatted data.
     * @throws IllegalArgumentException if CSV data format is incorrect
     */
    private String convertCSVToJson(String csvData) throws IllegalArgumentException {
        List<String> lines = Stream.of(csvData.split("\r?\n")).collect(Collectors.toList());
        if (lines.isEmpty()) throw new IllegalArgumentException("CSV data is empty");

        String[] headers = lines.get(0).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (headers.length == 0) throw new IllegalArgumentException("CSV header is missing");

        StringBuilder jsonBuilder = new StringBuilder("[\n");
        for (int i = 1; i < lines.size(); i++) {
            String[] cells = lines.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if (cells.length != headers.length) {
                throw new IllegalArgumentException("CSV data format is incorrect: mismatched columns on line " + (i + 1));
            }

            jsonBuilder.append("  {");
            for (int j = 0; j < headers.length; j++) {
                String cellValue = cells[j].replaceAll("^\"|\"$", ""); // Remove surrounding quotes if present
                jsonBuilder.append(String.format("\"%s\": \"%s\"", headers[j], cellValue));
                if (j < headers.length - 1) jsonBuilder.append(", ");
            }
            jsonBuilder.append("}");
            if (i < lines.size() - 1) jsonBuilder.append(",\n");
        }

        jsonBuilder.append("\n]");
        return jsonBuilder.toString().trim();
    }
}
