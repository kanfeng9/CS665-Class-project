package edu.bu.met.cs665.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for XMLValidator to ensure it correctly identifies well-formed and malformed XML data.
 */
public class XMLValidatorTest {

    @Test
    public void testValidXML() {
        XMLValidator validator = new XMLValidator();
        String xml = "<root><child>Content</child></root>";
        assertTrue(validator.validate(xml), "The validator should return true for well-formed XML.");
    }

    @Test
    public void testInvalidXML() {
        XMLValidator validator = new XMLValidator();
        String xml = "<root><child>Content</root>";
        assertFalse(validator.validate(xml), "The validator should return false for malformed XML.");
    }
}
