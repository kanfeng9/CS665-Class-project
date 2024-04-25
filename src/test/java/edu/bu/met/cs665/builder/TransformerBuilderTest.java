package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.transformers.CSVToJsonTransformer;
import edu.bu.met.cs665.transformers.DataTransformer;
import edu.bu.met.cs665.transformers.XMLToYAMLTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: TransformerBuilderTest.java
 * Description: This class tests the TransformerBuilder to ensure it correctly creates instances of
 * DataTransformer based on the specified configurations.
 */
public class TransformerBuilderTest {

    private static final Logger logger = LogManager.getLogger(TransformerBuilderTest.class);
    private TransformerBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new TransformerBuilder();
        logger.info("Setting up TransformerBuilder for testing.");
    }

    @Test
    public void testBuild_CSVToJsonTransformer() {
        try {
            DataTransformer transformer = builder.setType("CSVToJson").build();
            assertTrue(transformer instanceof CSVToJsonTransformer, "Should create an instance of CSVToJsonTransformer.");
            logger.info("Successfully created CSVToJsonTransformer.");
        } catch (Exception e) {
            logger.error("Failed to create CSVToJsonTransformer: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testBuild_XMLToYAMLTransformer() {
        try {
            DataTransformer transformer = builder.setType("XMLToYAML").build();
            assertTrue(transformer instanceof XMLToYAMLTransformer, "Should create an instance of XMLToYAMLTransformer.");
            logger.info("Successfully created XMLToYAMLTransformer.");
        } catch (Exception e) {
            logger.error("Failed to create XMLToYAMLTransformer: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testBuild_NoTypeSet_ThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> builder.build(),
                "Should throw IllegalStateException if no type is set.");
        assertEquals("Type must be set!", exception.getMessage(), "Exception message should match expected message.");
        logger.error("Caught expected IllegalStateException: {}", exception.getMessage());
    }

    @Test
    public void testBuild_UnknownType_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> builder.setType("UnknownType").build(),
                "Should throw IllegalArgumentException if type is unknown.");
        assertTrue(exception.getMessage().contains("Unknown transformer type"), "Exception message should contain 'Unknown transformer type'.");
        logger.error("Caught expected IllegalArgumentException: {}", exception.getMessage());
    }
}
