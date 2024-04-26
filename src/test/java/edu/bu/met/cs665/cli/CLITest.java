package edu.bu.met.cs665.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CLITest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream testIn;

    private Path inputFilePath;
    private Path outputFilePath;

    @BeforeEach
    public void setUp() throws IOException {
        // Redirect the output to capture system output for verification
        System.setOut(new PrintStream(outContent));

        // Create temporary files for testing
        inputFilePath = Paths.get("mock/input.csv");
        outputFilePath = Paths.get("mock/output.json");
        Files.createDirectories(inputFilePath.getParent()); // Ensure the directory exists
        Files.write(inputFilePath, "name,age\nAlice,30\nBob,25".getBytes()); // Write some sample CSV data
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Restore the original System.out
        System.setOut(originalOut);

        // Clean up test files
        Files.deleteIfExists(inputFilePath);
        Files.deleteIfExists(outputFilePath);
    }

    @Test
    public void testTransformCommandExecution() throws IOException {
        String input = "1\n1\n" + inputFilePath.toString() + "\n" + outputFilePath.toString() + "\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CLI.main(new String[]{});

        // Check that the output file was created and contains expected JSON
        assertTrue(Files.exists(outputFilePath));
        String jsonOutput = new String(Files.readAllBytes(outputFilePath));
        assertTrue(jsonOutput.contains("\"name\": \"Alice\"")); // Sample check
    }

    @Test
    public void testExitCommandExecution() {
        String input = "2\n";  // Directly exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CLI.main(new String[]{});

        // Check for the correct exit message
        assertTrue(outContent.toString().contains("Exiting the Data Transformation System. Goodbye!"));
    }

    @Test
    public void testInvalidCommandHandling() {
        String input = "invalid\n2\n";  // Input an invalid command then exit
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CLI.main(new String[]{});

        // Check for the handling of unknown commands
        assertTrue(outContent.toString().contains("Unknown command. Please try again."));
    }
}
