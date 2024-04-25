package edu.bu.met.cs665.transformers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/24/2024
 * File Name: CSVToJsonTransformerTest.java
 * Description: This class contains tests for the CSVToJsonTransformer to ensure it
 * correctly converts CSV data into JSON format.
 */
public class CSVToJsonTransformerTest {

    private static final Logger logger = LogManager.getLogger(CSVToJsonTransformerTest.class);
    private CSVToJsonTransformer transformer;

    @BeforeEach
    public void setUp() {
        transformer = new CSVToJsonTransformer();
        logger.info("CSVToJsonTransformer initialized for testing.");
    }

    @Test
    public void testTransform_simpleCSV_correctJson() {
        String csv = "name,age\nAlice,30\nBob,25";
        String expectedJson = "[{\"name\":\"Alice\",\"age\":\"30\"},{\"name\":\"Bob\",\"age\":\"25\"}]";
        try {
            String actualJson = transformer.transform(csv);
            assertEquals(expectedJson, actualJson, "The JSON output should match the expected simple JSON format.");
            logger.info("testTransform_simpleCSV_correctJson passed.");
        } catch (Exception e) {
            logger.error("Error in testTransform_simpleCSV_correctJson: " + e.getMessage(), e);
            fail("An exception was thrown in testTransform_simpleCSV_correctJson.");
        }
    }

    @Test
    public void testTransform_emptyCSV_emptyJsonArray() {
        String csv = "";
        String expectedJson = "[]";
        try {
            String actualJson = transformer.transform(csv);
            assertEquals(expectedJson, actualJson, "An empty CSV should result in an empty JSON array.");
            logger.info("testTransform_emptyCSV_emptyJsonArray passed.");
        } catch (Exception e) {
            logger.error("Error in testTransform_emptyCSV_emptyJsonArray: " + e.getMessage(), e);
            fail("An exception was thrown in testTransform_emptyCSV_emptyJsonArray.");
        }
    }

    @Test
    public void testTransform_headerOnly_emptyJsonArray() {
        String csv = "name,age";
        String expectedJson = "[]";
        try {
            String actualJson = transformer.transform(csv);
            assertEquals(expectedJson, actualJson, "A CSV with only headers should result in an empty JSON array.");
            logger.info("testTransform_headerOnly_emptyJsonArray passed.");
        } catch (Exception e) {
            logger.error("Error in testTransform_headerOnly_emptyJsonArray: {}", e.getMessage(), e);
            fail("An exception was thrown in testTransform_headerOnly_emptyJsonArray.");
        }
    }

    @Test
    public void testTransform_incorrectFormat_throwException() {
        String csv = "name,age\nAlice,30\nBob"; // Missing an age for Bob
        try {
            transformer.transform(csv);
            fail("An exception should have been thrown due to incorrect CSV format.");
        } catch (Exception e) {
            assertTrue(true, "An exception was expected due to incorrect CSV format.");
            logger.info("Expected exception caught in testTransform_incorrectFormat_throwException.");
        }
    }
}
