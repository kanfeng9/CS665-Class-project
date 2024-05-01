package edu.bu.met.cs665.cli;

import edu.bu.met.cs665.transformers.DataTransformer;
import edu.bu.met.cs665.transformers.TransformerFactory;

import java.util.Scanner;
import java.io.IOException;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: ConvertCommand.java
 * Description: This class implements the Command interface to handle the 'convert' command within the CLI.
 * It prompts the user to specify the type of data transformation, as well as input and output file paths,
 * and then initiates the transformation process using the specified transformer.
 */
public class ConvertCommand implements Command {
    private final Scanner scanner;

    /**
     * Constructor to initialize the ConvertCommand with a scanner to read input from the user.
     *
     * @param scanner The scanner to read command line input.
     */
    public ConvertCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Executes the transformation command by interacting with the user to get necessary details
     * and invoking the transformation logic.
     */
    @Override
    public void execute() {
        System.out.print("Enter the type of transformer (CSVToJson, XMLToYAML): ");
        CLI.getType(scanner);
    }
}
