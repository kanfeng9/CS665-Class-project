package edu.bu.met.cs665.validators;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: XMLValidator.java
 * Description: This class implements the Validator interface for XML data.
 * It checks for the well-formedness of XML by attempting to parse it. This validator ensures that the XML data
 * can be parsed without errors, indicating that it is structurally correct.
 */
public class XMLValidator implements Validator {

    /**
     * Validates an XML string to ensure it is well-formed by trying to parse it with a DocumentBuilder.
     *
     * @param data The XML data as a String.
     * @return boolean indicating whether the XML data is well-formed.
     */
    @Override
    public boolean validate(String data) {
        if (data == null || data.trim().isEmpty()) {
            return false;
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(data)));
            doc.getDocumentElement().normalize();  // If no exception is thrown, the XML is considered well-formed.
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // Log the error if required
            return false;  // Return false if any parsing errors occur
        }
    }
}
