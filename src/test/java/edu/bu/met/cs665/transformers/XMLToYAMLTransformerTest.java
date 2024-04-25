package edu.bu.met.cs665.transformers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: XMLToYAMLTransformerTest.java
 * Description: This class tests the XMLToYAMLTransformer to ensure it correctly converts XML data into YAML format.
 */
public class XMLToYAMLTransformerTest {

    private static final Logger logger = LogManager.getLogger(XMLToYAMLTransformerTest.class);
    private XMLToYAMLTransformer transformer;

    @BeforeEach
    public void setUp() {
        transformer = new XMLToYAMLTransformer();
        logger.info("XMLToYAMLTransformer initialized for testing.");
    }

    @Test
    public void testTransform_simpleXML_correctYaml() {
        String xml = "<person><name>Alice</name><age>30</age></person>";
        String expectedYaml = "person:\n  name: Alice\n  age: '30'\n";
        try {
            String actualYaml = transformer.transform(xml);
            assertEquals(expectedYaml, actualYaml, "The YAML output should match the expected simple YAML format.");
            logger.info("testTransform_simpleXML_correctYaml passed successfully.");
        } catch (Exception e) {
            logger.error("Failure in testTransform_simpleXML_correctYaml", e);
            fail("Unexpected error during test: " + e.getMessage());
        }
    }

    @Test
    public void testTransform_emptyXML_emptyYaml() {
        String xml = "";
        String expectedYaml = "{}\n"; // Expecting an empty YAML object
        try {
            String actualYaml = transformer.transform(xml);
            assertEquals(expectedYaml, actualYaml, "An empty XML should result in an empty YAML object.");
            logger.info("testTransform_emptyXML_emptyYaml passed successfully.");
        } catch (Exception e) {
            logger.error("Failure in testTransform_emptyXML_emptyYaml", e);
            fail("Unexpected error during test: " + e.getMessage());
        }
    }

    @Test
    public void testTransform_nestedXML_correctYaml() {
        String xml = "<company><department><name>HR</name><employee>Alice</employee></department></company>";
        String expectedYaml = "company:\n  department:\n    name: HR\n    employee: Alice\n";
        try {
            String actualYaml = transformer.transform(xml);
            assertEquals(expectedYaml, actualYaml, "The YAML output should correctly represent nested XML elements.");
            logger.info("testTransform_nestedXML_correctYaml passed successfully.");
        } catch (Exception e) {
            logger.error("Failure in testTransform_nestedXML_correctYaml", e);
            fail("Unexpected error during test: " + e.getMessage());
        }
    }


}
