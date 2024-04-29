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

public class XMLToYAMLTransformer implements DataTransformer {

    private static final Logger logger = LoggerFactory.getLogger(XMLToYAMLTransformer.class);

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

    private void handleEmptyInput(String outputFilePath) throws IOException {
        String emptyYaml = "{}\n";
        if (outputFilePath != null && !outputFilePath.trim().isEmpty()) {
            Files.write(Paths.get(outputFilePath), emptyYaml.getBytes());
        } else {
            System.out.println(emptyYaml);
        }
    }

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
                    // If this node name already exists in the map, it means we are dealing with a list
                    Object existingObject = dataMap.get(node.getNodeName());
                    if (existingObject instanceof List) {
                        @SuppressWarnings("unchecked")
                        List<Object> existingList = (List<Object>) existingObject;
                        existingList.add(childMap);
                    } else {
                        // Convert the existing object to a list
                        List<Object> newList = new ArrayList<>();
                        newList.add(existingObject);  // Add the previously mapped item
                        newList.add(childMap);        // Add the new item
                        dataMap.put(node.getNodeName(), newList);
                    }
                } else {
                    dataMap.put(node.getNodeName(), childMap);
                }
            }
        }
    }


    private static class CustomErrorHandler implements ErrorHandler {
        public void warning(SAXParseException e) throws SAXException {
            logger.warn("Warning: ", e);
        }

        public void error(SAXParseException e) throws SAXException {
            logger.error("Error: ", e);
        }

        public void fatalError(SAXParseException e) throws SAXException {
            logger.error("Fatal error: ", e);
            throw e; // Re-throw the error to ensure that it is still handled by the caller.
        }
    }
}
