package edu.bu.met.cs665.transformers;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: DataTransformer.java
 * Description: This interface defines the basic structure for data transformers.
 * All specific data transformers must implement this interface to ensure compatibility
 * across different data formats.
 */
public interface DataTransformer {

    /**
     * Transforms data from one format to another.
     * @param inputData String representation of the input data.
     * @return String representation of the transformed data.
     */
    String transform(String inputData);

}
