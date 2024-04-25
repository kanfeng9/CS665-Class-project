package edu.bu.met.cs665.transformers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: XMLToYAMLTransformer.java
 * Description: This class implements the DataTransformer interface to convert XML data into YAML format.
 * It uses recursive parsing for XML and outputs a YAML formatted string using the SnakeYAML library.
 */
public class XMLToYAMLTransformer implements DataTransformer {

    private static final Logger logger = LoggerFactory.getLogger(XMLToYAMLTransformer.class);

    /**
     * Transforms XML data to YAML format.
     * @param inputData String representation of the input XML data.
     * @return String representation of the YAML formatted data, or null if an error occurs.
     */
    public String transform(String inputData) {
        if (inputData == null || inputData.trim().isEmpty()) {
            return "{}\n";  // Return an empty YAML object for empty input.
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(inputData));
            source.setEncoding("UTF-8");
            Document doc = dBuilder.parse(source);
            doc.getDocumentElement().normalize();

            Map<String, Object> dataMap = new LinkedHashMap<>();
            buildMapFromNode(doc.getDocumentElement(), dataMap);

            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            return yaml.dump(dataMap);
        } catch (SAXException e) {
            logger.error("Invalid XML structure: ", e);
            return "error: Invalid XML structure\n";
        } catch (IOException | ParserConfigurationException e) {
            logger.error("Error processing XML: ", e);
            return "error: Error processing XML\n";
        }
    }





    /**
     * Recursive method to parse an XML Node and build a map that represents its structure.
     * This method populates the map based on node names and their content or attributes.
     * @param node XML Node to be processed.
     * @param map The map to which the node's data should be added.
     */
    private void buildMapFromNode(Node node, Map<String, Object> map) {
        // Process only element nodes
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            NodeList childNodes = node.getChildNodes();
            if (childNodes.getLength() == 1 && childNodes.item(0).getNodeType() == Node.TEXT_NODE) {
                map.put(node.getNodeName(), node.getTextContent());
            } else {
                Map<String, Object> subMap = new LinkedHashMap<>();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    buildMapFromNode(childNodes.item(i), subMap);
                }
                if (!subMap.isEmpty()) {
                    map.put(node.getNodeName(), subMap);
                }
            }
        }
    }
}
