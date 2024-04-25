| CS-665       | Software Design & Patterns |
|--------------|----------------------------|
| Name         | Zhiling Li                 |
| Date         | 04/25/2024                 |
| Course       | Spring                     |
| Assignment # | final                      |

## Assignment Overview

### Project Purpose

The primary objective of this project is to demonstrate the application of advanced software design principles and
patterns in a practical, real-world scenario. The focus is on building a robust data transformation tool that can
convert data from one format to another, such as CSV to JSON or XML to YAML. This tool is designed to be extensible,
maintainable, and easy to use, addressing typical challenges faced in software development such as scalability,
flexibility, and code reusability.

### Project Scope

The project encompasses the following key functionalities:

- **Data Transformation**: Core functionality to transform data from one format to another using various algorithms
  encapsulated in different transformer classes.
- **Extensibility**: Ability to easily add new data formats and transformation logic without impacting existing
  functionality.
- **Error Handling**: Robust error handling to manage and log issues during the data transformation processes.
- **Testing**: Comprehensive unit tests to ensure reliability and correctness of the transformation logic.

### Approach

The project leverages several design patterns and best practices in software engineering:

- **Factory Method Pattern**: For creating transformer instances, allowing for easy addition of new transformers.
- **Builder Pattern**: To construct complex transformer configurations in a step-by-step manner.
- **Singleton Pattern**: Ensured that certain utility classes were instantiated only once, managing shared resources
  efficiently.
- **Delegation Pattern**: To delegate specific tasks like encoding conversion and data cleaning to specialized classes,
  thereby keeping the transformer classes focused on core responsibilities.

This structured approach not only aids in achieving the project's goals but also serves as a practical demonstration of
applying theoretical software design concepts in real-world applications.


# GitHub Repository Link:

https://github.com/kanfeng9/CS665-Class-project

## Implementation Description

### Flexibility

The implementation of this application emphasizes flexibility through the use of design patterns that allow for easy
extension and modification of code. For instance, the **Factory Method** pattern is utilized within
the `TransformerFactory` to facilitate the addition or removal of new transformer types without altering existing code.
This makes it straightforward to integrate new data formats or transformation techniques as the application evolves.

### Simplicity and Understandability

To ensure that the application is easy to understand and maintain, the codebase is structured around clear and simple
design principles:

- **Single Responsibility Principle (SRP)**: Each class and method has a single responsibility and is independent of the
  complexities of other features. For example, `CSVToJsonTransformer` focuses solely on converting CSV data to JSON
  format, devoid of any additional logic.
- **Modular Design**: The application is divided into modules such as transformers, utilities, and delegates. This
  separation enhances readability and maintainability by isolating specific functionalities.

### Avoiding Duplicated Code

Duplicated code has been meticulously avoided by:

- **Using Abstract Classes and Interfaces**: Common functionalities are abstracted into interfaces or base classes. For
  example, all transformers implement the `DataTransformer` interface, which standardizes the transformation process and
  reduces code repetition.
- **Utility Classes**: Shared methods, such as file operations and configuration loading, are centralized in utility
  classes (`FileUtil` and `ConfigLoader`), ensuring that these methods are written once and reused throughout the
  application.

### Use of Design Patterns

Several design patterns have been employed to address specific challenges:

- **Factory Method Pattern**: Used in `TransformerFactory` to create instances of transformers, enabling the decoupling
  of object creation from its usage.
- **Builder Pattern**: Implemented in `TransformerBuilder` to provide a flexible solution to various object creation
  scenarios in the transformers.
- **Singleton Pattern**: Applied in utility classes to ensure that only a single instance of a class is created,
  providing a controlled access point to resources, such as configuration settings.
- **Decorator Pattern**: Although not used in the current implementation, this pattern was considered for potential
  future enhancements to add additional behaviors to transformers without modifying their code.

These patterns were chosen not only for their technical benefits but also to facilitate a deeper understanding of good
design practices and their impact on maintainability and scalability.


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

JUnit is a popular testing framework for Java. JUnit tests are automated tests that are written to verify that the
behavior of a piece of code is as expected.

In JUnit, tests are written as methods within a test class. Each test method tests a specific aspect of the code and is
annotated with the @Test annotation. JUnit provides a range of assertions that can be used to verify the behavior of the
code being tested.

JUnit tests are executed automatically and the results of the tests are reported. This allows developers to quickly and
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




