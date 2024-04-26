package edu.bu.met.cs665.cli;

import edu.bu.met.cs665.transformers.DataTransformer;
import edu.bu.met.cs665.transformers.TransformerFactory;
import edu.bu.met.cs665.validators.Validator;

import java.util.Scanner;
import java.io.IOException;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: CLI.java
 * Description: This class provides a command-line interface for users to interact with the
 * data transformation system. It allows users to execute transformations and exit the application.
 */
public class CLI {
    private final Scanner scanner;

    /**
     * Constructor initializes the scanner used to read user input.
     */
    public CLI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main method to run the CLI application.
     */
    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.start();
    }

    /**
     * Starts the command-line interface, continuously prompts the user for commands until 'exit' is entered.
     */
    public void start() {
        System.out.println("Welcome to the Data Transformation System");
        System.out.println("Available Commands: 1 for transform, 2 for exit");

        while (true) {
            System.out.print("Enter command (transform, exit): ");
            String commandInput = scanner.nextLine();

            if (commandInput.equalsIgnoreCase("2")) {
                System.out.println("Exiting the Data Transformation System. Goodbye!");
                break;
            } else if (commandInput.equalsIgnoreCase("1")) {
                executeTransformCommand();
            } else {
                System.out.println("Unknown command. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Handles the 'transform' command, asking for necessary input and output file paths,
     * and invoking the transformation process.
     */
    private void executeTransformCommand() {
        System.out.print("Enter the type of transformer (1 for CSVToJson, 2 for XMLToYAML): ");
        getType(scanner);
    }

    static void getType(Scanner scanner) {
        String transformerType = scanner.nextLine();
        DataTransformer transformer = TransformerFactory.getTransformer(transformerType);

        if (transformer == null) {
            System.out.println("Invalid transformer type. Please try again.");
            return;
        }

        System.out.print("Enter the input file path: ");
        String inputFilePath = scanner.nextLine();
        System.out.print("Enter the output file path (leave empty for console output): ");
        String outputFilePath = scanner.nextLine().trim();
        if (outputFilePath.isEmpty()) {
            outputFilePath = null; // Output to console
        }

        try {
            transformer.transform(inputFilePath, outputFilePath);
            if (outputFilePath != null) {
                System.out.println("Transformation completed successfully. Output file: " + outputFilePath);
            } else {
                System.out.println("Transformation completed, result printed to console.");
            }
        } catch (IOException e) {
            System.err.println("Error during file transformation: " + e.getMessage());
        }
    }
}
