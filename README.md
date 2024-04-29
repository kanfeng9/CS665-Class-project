| CS-665       | Software Design & Patterns |
|--------------|----------------------------|
| Name         | Zhiling Li                 |
| Date         | 04/28/2024                 |
| Course       | Spring                     |
| Assignment # | final                      |

## Assignment Overview

## Assignment Overview

This project, part of the CS-665 Software Designs & Patterns curriculum at Boston University, implements a data
transformation system with a focus on software design patterns. The system provides a means to convert data between
various formats such as CSV, JSON, XML, and YAML, all through an interactive command-line interface.

### Key Features

- **Multiple Data Formats**: Transform data between popular data formats like CSV, JSON, XML, and YAML.
- **CLI**: Use the command-line interface for easy interaction with the system, allowing for file specification and
  transformation selection.
- **Concurrency Management**: Efficient processing with concurrency support to handle large data transformations.
- **Extensible Design**: Apply design patterns to allow easy addition of new data formats and features.

### Modules

The project consists of several key modules:

- **builder**: Contains the `TransformerBuilder` class for constructing transformer instances.
- **cli**: Handles the command-line user interface, processing user commands.
  - `CLI`: The main class for the command-line interface.
  - `Command`: An interface for command actions.
  - `ConvertCommand`: Implements the `Command` interface to manage the conversion process.
- **concurrency**: Manages concurrent data processing.
  - `DataProcessor`: Processes data using concurrency.
  - `ThreadPoolManager`: Manages thread pools for concurrent tasks.
- **delegation**: Utilizes the delegation pattern for specific processing tasks.
  - `CleaningDelegate`: Handles cleaning aspects of data transformation.
  - `EncodingDelegate`: Manages encoding during data transformation.
- **transformers**: Classes that define the transformation logic.
  - `CSVToJsonTransformer`: Transformer for CSV to JSON conversions.
  - `DataTransformer`: An interface for all transformers.
  - `TransformerFactory`: Factory class for creating transformer instances.
  - `XMLToYAMLTransformer`: Transformer for XML to YAML conversions.
- **util**: Utility classes for common operations.
  - `ConfigLoader`: Loads configuration settings.
  - `FileUtil`: Provides file-related utility functions.
- **validators**: Validates input data formats.
  - `CSVValidator`: Validates CSV data format.
  - `Validator`: An interface for validation logic.
  - `XMLValidator`: Validates XML data format.

### Usage

To use the system, navigate to the compiled classes' directory and execute:

```bash
java edu.bu.met.cs665.cli.CLI
```


# GitHub Repository Link:

https://github.com/kanfeng9/CS665-Class-project
## Implementation Description

The Data Transformation System (DTS) is designed with extensibility and maintainability in mind, leveraging various
design patterns to facilitate these principles.

### Design Patterns

- **Factory Pattern**: The `TransformerFactory` class in the `transformers` package abstracts the instantiation of
  different transformers. This pattern allows the addition of new transformers without modifying existing code, adhering
  to the open-closed principle.

- **Singleton Pattern**: The `ConfigLoader` class in the `util` package ensures that configuration settings are loaded
  only once and are accessible globally without redundant I/O operations.

- **Command Pattern**: The `cli` package utilizes the Command pattern, encapsulating command execution logic in
  the `ConvertCommand` class, allowing for easy addition of new commands.

- **Strategy Pattern**: Validation logic is abstracted behind the `Validator` interface in the `validators` package.
  Specific validation strategies like `CSVValidator` and `XMLValidator` implement this interface, making the system
  adaptable to new data formats.

- **Builder Pattern**: The `TransformerBuilder` class in the `builder` package provides a step-by-step approach to
  creating complex transformer objects, making the construction process customizable and clear.

- **Delegation Pattern**: The `CleaningDelegate` and `EncodingDelegate` classes within the `delegation` package handle
  specific subtasks of data transformation, following the single responsibility principle.

- **Thread Pool Pattern**: The `ThreadPoolManager` in the `concurrency` package manages a pool of threads to execute
  data processing tasks concurrently, improving performance for large-scale data transformations.

### Project Modules

Each module and class has a distinct responsibility:

- **builder**: Constructs transformers using the Builder pattern.
- **cli**: Manages user input and system output, serving as the system's entry point.
- **concurrency**: Manages concurrent execution of tasks to enhance performance.
- **delegation**: Delegates specific tasks to specialized classes for cleaner code.
- **transformers**: Converts data between different formats.
- **util**: Provides utility functions central to the application's operation.
- **validators**: Checks the validity of the input data before processing.

### Concurrency Management

The DTS processes large data transformations concurrently, ensuring efficient use of system resources.
The `ThreadPoolManager` oversees thread allocation and task execution, while the `DataProcessor` manages the data
processing workflow.

### Data Validation

Before transforming data, the DTS validates input files using the appropriate `Validator`. This step ensures data
correctness and format compliance, preventing processing errors down the line.

### User Interface

The CLI module provides a user-friendly interface for interacting with the DTS. Users are guided through the
transformation process with clear instructions and feedback, simplifying the user experience.

### Logging

Logging is facilitated by SLF4J and Logback, providing insight into the system's operational state and aiding in
debugging and monitoring. The system logs significant events and errors to both the console and external log files for
review.

### Testing

Robust unit tests validate each component's functionality. The tests ensure that the system behaves as expected, even as
new features are integrated.

---

By using these design patterns and organizing the project into clear modules, the DTS achieves a high degree of
modularity and readability. This structure supports ongoing development and ensures the system can evolve to meet future
requirements.


# Maven Commands

We'll use Apache Maven to compile and run this project. You'll need to install Apache Maven (https://maven.apache.org/)
on your system.

Apache Maven is a build automation tool and a project management tool for Java-based projects. Maven provides a
standardized way to build, package, and deploy Java applications.

Maven uses a Project Object Model (POM) file to manage the build process and its dependencies. The POM file contains
information about the project, such as its dependencies, the build configuration, and the plugins used for building and
packaging the project.

Maven provides a centralized repository for storing and accessing dependencies, which makes it easier to manage the
dependencies of a project. It also provides a standardized way to build and deploy projects, which helps to ensure that
builds are consistent and repeatable.

Maven also integrates with other development tools, such as IDEs and continuous integration systems, making it easier to
use as part of a development workflow.

Maven provides a large number of plugins for various tasks, such as compiling code, running tests, generating reports,
and creating JAR files. This makes it a versatile tool that can be used for many different types of Java projects.

## Compile

Type on the command line:

```bash
mvn clean compile
```

## JUnit Tests

JUnit is a popular testing framework for Java. JUnit test are automated tests that are written to verify that the
behavior of a piece of code is as expected.

In JUnit, tests are written as methods within a test class. Each test method tests a specific aspect of the code and is
annotated with the @Test annotation. JUnit provides a range of assertions that can be used to verify the behavior of the
code being tested.

JUnit test are executed automatically and the results of the tests are reported. This allows developers to quickly and
easily check if their code is working as expected, and make any necessary changes to fix any issues that are found.

The use of JUnit tests is an important part of Test-Driven Development (TDD), where tests are written before the code
they are testing is written. This helps to ensure that the code is written in a way that is easily testable and that all
required functionality is covered by tests.

JUnit tests can be run as part of a continuous integration pipeline, where tests are automatically run every time
changes are made to the code. This helps to catch any issues as soon as they are introduced, reducing the need for
manual testing and making it easier to ensure that the code is always in a releasable state.

To run, use the following command:
```bash
mvn clean test
```

## Spotbugs

SpotBugs is a static code analysis tool for Java that detects potential bugs in your code. It is an open-source tool
that can be used as a standalone application or integrated into development tools such as Eclipse, IntelliJ, and Gradle.

SpotBugs performs an analysis of the bytecode generated from your Java source code and reports on any potential problems
or issues that it finds. This includes things like null pointer exceptions, resource leaks, misused collections, and
other common bugs.

The tool uses data flow analysis to examine the behavior of the code and detect issues that might not be immediately
obvious from just reading the source code. SpotBugs is able to identify a wide range of issues and can be customized to
meet the needs of your specific project.

Using SpotBugs can help to improve the quality and reliability of your code by catching potential bugs early in the
development process. This can save time and effort in the long run by reducing the need for debugging and fixing issues
later in the development cycle. SpotBugs can also help to ensure that your code is secure by identifying potential
security vulnerabilities.

Use the following command:

```bash
mvn spotbugs:gui 
```

For more info see
https://spotbugs.readthedocs.io/en/latest/maven.html

SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.

## Checkstyle

Checkstyle is a development tool for checking Java source code against a set of coding standards. It is an open-source
tool that can be integrated into various integrated development environments (IDEs), such as Eclipse and IntelliJ, as
well as build tools like Maven and Gradle.

Checkstyle performs static code analysis, which means it examines the source code without executing it, and reports on
any issues or violations of the coding standards defined in its configuration. This includes issues like code style,
code indentation, naming conventions, code structure, and many others.

By using Checkstyle, developers can ensure that their code adheres to a consistent style and follows best practices,
making it easier for other developers to read and maintain. It can also help to identify potential issues before the
code is actually run, reducing the risk of runtime errors or unexpected behavior.

Checkstyle is highly configurable and can be customized to fit the needs of your team or organization. It supports a
wide range of coding standards and can be integrated with other tools, such as code coverage and automated testing
tools, to create a comprehensive and automated software development process.

The following command will generate a report in HTML format that you can open in a web browser.

```bash
mvn checkstyle:checkstyle
```

The HTML page will be found at the following location:
`target/site/checkstyle.html`




