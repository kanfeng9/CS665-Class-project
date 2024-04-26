package edu.bu.met.cs665.cli;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: Command.java
 * Description: This interface defines a common structure for commands within the CLI of the
 * data transformation system. Implementations of this interface execute specific actions in response to
 * user inputs.
 */
public interface Command {
    /**
     * Executes the command's specific action.
     */
    void execute();
}
