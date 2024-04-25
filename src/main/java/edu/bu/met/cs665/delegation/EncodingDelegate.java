package edu.bu.met.cs665.delegation;

import java.nio.charset.StandardCharsets;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/24/2024
 * File Name: EncodingDelegate.java
 * Description: This class is responsible for handling encoding conversions for data.
 * It provides methods to convert data between different character encodings, focusing on
 * ensuring data integrity and compatibility.
 */
public class EncodingDelegate {

    /**
     * Converts the data from one encoding format to another. This example specifically
     * converts from ISO-8859-1 to UTF-8, which are commonly used character encodings.
     * @param data The data in original encoding (ISO-8859-1).
     * @return The data converted to UTF-8 encoding.
     */
    public String convertIso88591ToUtf8(String data) {
        byte[] isoBytes = data.getBytes(StandardCharsets.ISO_8859_1);
        return new String(isoBytes, StandardCharsets.UTF_8);
    }

    /**
     * Converts the data from UTF-8 encoding to ISO-8859-1. This is useful for compatibility
     * with systems that require ISO-8859-1 encoding.
     * @param data The data in UTF-8 encoding.
     * @return The data converted to ISO-8859-1 encoding.
     */
    public String convertUtf8ToIso88591(String data) {
        byte[] utf8Bytes = data.getBytes(StandardCharsets.UTF_8);
        return new String(utf8Bytes, StandardCharsets.ISO_8859_1);
    }
}
