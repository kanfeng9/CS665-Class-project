package edu.bu.met.cs665.builder;

import edu.bu.met.cs665.transformers.CSVToJsonTransformer;
import edu.bu.met.cs665.transformers.XMLToYAMLTransformer;
import edu.bu.met.cs665.transformers.DataTransformer;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/24/2024
 * File Name: TransformerBuilder.java
 * Description: This builder class is used for creating and configuring instances of DataTransformer.
 * It allows for detailed configuration of transformers before their creation, ensuring flexibility in
 * object creation.
 */
public class TransformerBuilder {

    private String type;

    public TransformerBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public TransformerBuilder setCustomConfig(String customConfig) {
        return this;
    }

    /**
     * Builds the DataTransformer based on the type and configuration provided.
     * @return DataTransformer based on the specified parameters.
     */
    public DataTransformer build() {
        if (type == null) {
            throw new IllegalStateException("Type must be set!");
        }

        DataTransformer transformer;
        switch (type) {
            case "CSVToJson":
                transformer = new CSVToJsonTransformer();
                // Here you can configure your transformer based on customConfig
                break;
            case "XMLToYAML":
                transformer = new XMLToYAMLTransformer();
                // Apply any specific configurations if necessary
                break;
            default:
                throw new IllegalArgumentException("Unknown transformer type: " + type);
        }

        return transformer;
    }
}
