package edu.bu.met.cs665.transformers;

import java.io.IOException;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: DataTransformer.java
 * Description: This interface defines the method that all data transformers must implement.
 * It requires transformers to be able to read from a source file and optionally write to an output file.
 */
public interface DataTransformer {
    /**
     * Transforms data from the source file specified by inputFilePath and writes the result
     * to the destination specified by outputFilePath.
     * @param inputFilePath Path to the input file containing raw data.
     * @param outputFilePath Path to the output file for transformed data; if null, output to console.
     * @throws IOException If file operations fail.
     */
    void transform(String inputFilePath, String outputFilePath) throws IOException;
}
