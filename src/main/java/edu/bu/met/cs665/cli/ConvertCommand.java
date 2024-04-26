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
 * Description: This command facilitates the data transformation process within the CLI.
 * It interacts with the user to specify input and output file paths and executes the transformation.
 */
public class ConvertCommand implements Command {
    private Scanner scanner;

    public ConvertCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter the type of transformer (CSVToJson, XMLToYAML): ");
        CLI.getType(scanner);
    }
}
