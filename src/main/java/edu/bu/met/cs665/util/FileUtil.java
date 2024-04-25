package edu.bu.met.cs665.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/24/2024
 * File Name: FileUtil.java
 * Description: This utility class provides methods to read from and write to files,
 * facilitating easy data handling for transformations.
 */
public class FileUtil {

    /**
     * Reads data from a file.
     * @param path Path to the file.
     * @return Data as a String.
     * @throws IOException If an error occurs while reading the file.
     */
    public static String readFile(String path) throws IOException {
        return Files.lines(Paths.get(path)).collect(Collectors.joining("\n"));
    }

    /**
     * Writes data to a file.
     * @param data Data to write.
     * @param path Path to the file where data should be written.
     * @throws IOException If an error occurs while writing to the file.
     */
    public static void writeFile(String data, String path) throws IOException {
        Files.write(Paths.get(path), data.getBytes());
    }
}
