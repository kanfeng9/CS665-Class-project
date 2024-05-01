package edu.bu.met.cs665.transformers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.IOException;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: XMLToYAMLTransformer.java
 * Description: This class implements the DataTransformer interface to convert XML data from a file into YAML format.
 * It parses XML using DOM, handles errors with a custom error handler, and outputs a YAML formatted string using the SnakeYAML library.
 */
public class XMLToYAMLTransformer implements DataTransformer {

    private static final Logger logger = LoggerFactory.getLogger(XMLToYAMLTransformer.class);

    /**
     * Transforms XML content from a file into a YAML format and writes it to an output file or standard output.
     *
     * @param inputFilePath  Path to the input XML file.
     * @param outputFilePath Path to the output YAML file (optional).
     * @throws IOException if file reading or writing operations fail or if XML parsing errors occur.
     */
    @Override
    public void transform(String inputFilePath, String outputFilePath) throws IOException {
        if (inputFilePath == null || inputFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Input file path cannot be null or empty.");
        }

        String inputData = new String(Files.readAllBytes(Paths.get(inputFilePath)));
        if (inputData.trim().isEmpty()) {
            handleEmptyInput(outputFilePath);
            return;
        }

        String yamlOutput;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.setErrorHandler(new CustomErrorHandler()); // Set custom error handler
            InputSource source = new InputSource(new StringReader(inputData));
            Document doc = dBuilder.parse(source);
            doc.getDocumentElement().normalize();

            Map<String, Object> dataMap = new LinkedHashMap<>();
            buildMapFromNode(doc.getDocumentElement(), dataMap);

            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            yamlOutput = yaml.dump(dataMap);
        } catch (SAXException | ParserConfigurationException e) {
            logger.error("Error processing XML to YAML transformation: ", e);
            throw new IOException("Error processing XML to YAML transformation: " + e.getMessage(), e);
        }

        if (outputFilePath != null && !outputFilePath.trim().isEmpty()) {
            Files.write(Paths.get(outputFilePath), yamlOutput.getBytes());
        } else {
            System.out.println(yamlOutput);
        }
    }

    /**
     * Handles the case when the input XML data is empty.
     * @param outputFilePath Path to the output YAML file (optional).
     * @throws IOException if there is an error writing to the file.
     */
    private void handleEmptyInput(String outputFilePath) throws IOException {
        String emptyYaml = "{}\n";
        if (outputFilePath != null && !outputFilePath.trim().isEmpty()) {
            Files.write(Paths.get(outputFilePath), emptyYaml.getBytes());
        } else {
            System.out.println(emptyYaml);
        }
    }

    /**
     * Recursively builds a map from an XML node. If a node has children, this method is called recursively.
     * @param node The current node to process.
     * @param dataMap The map where the data is being built.
     */
    private void buildMapFromNode(Node node, Map<String, Object> dataMap) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Map<String, Object> childMap = new LinkedHashMap<>();
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    if (item.getChildNodes().getLength() == 1 && item.getFirstChild().getNodeType() == Node.TEXT_NODE) {
                        childMap.put(item.getNodeName(), item.getTextContent().trim());
                    } else {
                        buildMapFromNode(item, childMap);
                    }
                }
            }
            if (!childMap.isEmpty()) {
                if (dataMap.containsKey(node.getNodeName())) {
                    // Handle duplicate node names by creating a list of maps
                    Object existingObject = dataMap.get(node.getNodeName());
                    if (existingObject instanceof List) {
                        @SuppressWarnings("unchecked")
                        List<Object> existingList = (List<Object>) existingObject;
                        existingList.add(childMap);
                    } else {
                        List<Object> newList = new ArrayList<>();
                        newList.add(existingObject);
                        newList.add(childMap);
                        dataMap.put(node.getNodeName(), newList);
                    }
                } else {
                    dataMap.put(node.getNodeName(), childMap);
                }
            }
        }
    }

    /**
     * Custom error handler for XML parsing errors.
     */
    private static class CustomErrorHandler implements ErrorHandler {
        public void warning(SAXParseException e) throws SAXException {
            logger.warn("Warning: ", e);
        }

        public void error(SAXParseException e) throws SAXException {
            logger.error("Error: ", e);
        }

        public void fatalError(SAXParseException e) throws SAXException {
            logger.error("Fatal error: ", e);
            throw e; // Ensure that fatal errors halt the transformation.
        }
    }
}
