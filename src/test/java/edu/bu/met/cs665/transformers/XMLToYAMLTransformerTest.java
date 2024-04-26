package edu.bu.met.cs665.transformers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/24/2024
 * File Name: XMLToYAMLTransformerTest.java
 * Description: This class tests the XMLToYAMLTransformer to ensure it correctly converts XML data into YAML format,
 * handling file inputs and outputs, and covering various scenarios including complex XML structures and malformed inputs.
 */
public class XMLToYAMLTransformerTest {

    private static final Logger logger = LoggerFactory.getLogger(XMLToYAMLTransformerTest.class);
    private XMLToYAMLTransformer transformer;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        transformer = new XMLToYAMLTransformer();
    }

    @Test
    public void testTransform_simpleXML_correctYaml() throws Exception {
        Path inputPath = tempDir.resolve("simple.xml");
        Path outputPath = tempDir.resolve("output.yaml");
        String xml = "<person><name>Alice</name><age>30</age></person>";
        Files.writeString(inputPath, xml);

        transformer.transform(inputPath.toString(), outputPath.toString());

        String actualYaml = Files.readString(outputPath).trim();
        String expectedYaml = "person:\n  name: Alice\n  age: '30'";
        assertEquals(expectedYaml, actualYaml, "The YAML output should match the expected simple YAML format.");
    }

    @Test
    public void testTransform_withAttributes_ignoresAttributes() throws Exception {
        Path inputPath = tempDir.resolve("withAttributes.xml");
        Path outputPath = tempDir.resolve("output.yaml");
        String xml = "<person age='30'><name>Alice</name></person>";
        Files.writeString(inputPath, xml);

        transformer.transform(inputPath.toString(), outputPath.toString());

        String actualYaml = Files.readString(outputPath).trim();
        String expectedYaml = "person:\n  name: Alice";
        assertEquals(expectedYaml, actualYaml, "The YAML output should ignore XML attributes and correctly represent nested elements.");
    }

    @Test
    public void testTransform_deepNestedXML_correctYaml() throws Exception {
        Path inputPath = tempDir.resolve("deepNested.xml");
        Path outputPath = tempDir.resolve("output.yaml");
        String xml = "<company><department><team><member>Alice</member></team></department></company>";
        Files.writeString(inputPath, xml);

        transformer.transform(inputPath.toString(), outputPath.toString());

        String actualYaml = Files.readString(outputPath).trim();
        String expectedYaml = "company:\n  department:\n    team:\n      member: Alice";
        assertEquals(expectedYaml, actualYaml, "The YAML output should correctly represent deeply nested XML elements.");
    }

    @Test
    public void testTransform_malformedXML_throwsException() {
        Path inputPath = tempDir.resolve("malformed.xml");
        String xml = "<person><name>Alice</name><age>30</person>";  // Incorrectly closed tag
        IOException exception = assertThrows(IOException.class, () -> {
            Files.writeString(inputPath, xml);
            transformer.transform(inputPath.toString(), null);
        }, "Malformed XML should cause an IOException due to error in XML processing.");

        assertTrue(exception.getMessage().contains("元素类型 \"age\" 必须由匹配的结束标记 \"</age>\" 终止"), "Exception message should mention the specific XML error about unmatched tags.");
    }

}
