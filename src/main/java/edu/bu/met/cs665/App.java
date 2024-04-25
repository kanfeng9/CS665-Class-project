package edu.bu.met.cs665;

import edu.bu.met.cs665.transformers.DataTransformer;
import edu.bu.met.cs665.transformers.TransformerFactory;
import edu.bu.met.cs665.delegation.CleaningDelegate;
import edu.bu.met.cs665.delegation.EncodingDelegate;
import edu.bu.met.cs665.util.ConfigLoader;

import java.io.IOException;
import java.util.Scanner;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: App.java
 * Description: This application demonstrates the use of various data transformers with additional
 * preprocessing and postprocessing steps. Users can input data, select a transformer type, and view the
 * transformed data.
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            ConfigLoader configLoader = new ConfigLoader("config.properties");
            String defaultTransformer = configLoader.getProperty("defaultTransformer");

            System.out.println("Welcome to the Data Transformer System");
            System.out.print("Enter the type of transformer (CSVToJson, XMLToYAML), or press Enter for default (" + defaultTransformer + "): ");
            String type = scanner.nextLine().trim();
            if (type.isEmpty()) {
                type = defaultTransformer;
            }

            System.out.print("Enter the data to transform: ");
            String data = scanner.nextLine();

            // Preprocessing
            CleaningDelegate cleaningDelegate = new CleaningDelegate();
            String cleanedData = cleaningDelegate.cleanData(data);

            // Transformation
            DataTransformer transformer = TransformerFactory.getTransformer(type);
            if (transformer != null) {
                EncodingDelegate encodingDelegate = new EncodingDelegate();
                // Assuming the transformer needs data in UTF-8 but received in ISO-8859-1
                String encodedData = encodingDelegate.convertIso88591ToUtf8(cleanedData);
                String result = transformer.transform(encodedData);

                // Output result
                System.out.println("Transformed Data: ");
                System.out.println(result);
            } else {
                System.out.println("Invalid transformer type provided.");
            }
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
