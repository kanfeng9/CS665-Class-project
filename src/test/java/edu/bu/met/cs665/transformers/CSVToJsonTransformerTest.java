package edu.bu.met.cs665.transformers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/24/2024
 * File Name: CSVToJsonTransformerTest.java
 * Description: This class contains tests for the CSVToJsonTransformer to ensure it
 * correctly converts CSV data into JSON format, handling file inputs and outputs.
 */
public class CSVToJsonTransformerTest {

    private static final Logger logger = LogManager.getLogger(CSVToJsonTransformerTest.class);
    private CSVToJsonTransformer transformer;

    @TempDir
    Path tempDir;  // JUnit Jupiter provides a temporary directory for file operations

    @BeforeEach
    public void setUp() {
        transformer = new CSVToJsonTransformer();
        logger.info("CSVToJsonTransformer initialized for testing.");
    }

    @Test
    public void testTransform_simpleCSV_correctJson() throws Exception {
        Path inputPath = tempDir.resolve("input.csv");
        Path outputPath = tempDir.resolve("output.json");
        String csv = "name,age\nAlice,30\nBob,25";
        String expectedJson = "[\n  {\"name\": \"Alice\", \"age\": \"30\"},\n  {\"name\": \"Bob\", \"age\": \"25\"}\n]";

        Files.writeString(inputPath, csv);
        transformer.transform(inputPath.toString(), outputPath.toString());

        String actualJson = Files.readString(outputPath);
        assertEquals(expectedJson, actualJson.trim(), "The JSON output should match the expected simple JSON format.");
        logger.info("testTransform_simpleCSV_correctJson passed.");
    }


    @Test
    public void testTransform_emptyCSV_emptyJsonArray() throws Exception {
        Path inputPath = tempDir.resolve("empty.csv");
        Path outputPath = tempDir.resolve("emptyOutput.json");
        String csv = "";
        String expectedJson = "[\n\n]";

        Files.writeString(inputPath, csv);
        transformer.transform(inputPath.toString(), outputPath.toString());

        String actualJson = Files.readString(outputPath);
        assertEquals(expectedJson, actualJson.trim(), "An empty CSV should result in an empty JSON array.");
        logger.info("testTransform_emptyCSV_emptyJsonArray passed.");
    }

    @Test
    public void testTransform_headerOnly_emptyJsonArray() throws Exception {
        Path inputPath = tempDir.resolve("headerOnly.csv");
        Path outputPath = tempDir.resolve("headerOnlyOutput.json");
        String csv = "name,age";
        String expectedJson = "[\n\n]";

        Files.writeString(inputPath, csv);
        transformer.transform(inputPath.toString(), outputPath.toString());

        String actualJson = Files.readString(outputPath);
        assertEquals(expectedJson, actualJson.trim(), "A CSV with only headers should result in an empty JSON array.");
        logger.info("testTransform_headerOnly_emptyJsonArray passed.");
    }

    @Test
    public void testTransform_incorrectFormat_throwException() {
        Path inputPath = tempDir.resolve("incorrectFormat.csv");
        String csv = "name,age\nAlice,30\nBob"; // Missing an age for Bob

        Exception exception = assertThrows(IOException.class, () -> {
            Files.writeString(inputPath, csv);
            transformer.transform(inputPath.toString(), null);
        });
        // Check that the exception message contains the expected text
        assertTrue(exception.getMessage().contains("CSV data format is incorrect"), "Expected CSV format error did not occur.");
    }




}
