package edu.bu.met.cs665.transformers;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

/**
 * Name: Zhiling Li
 * Course: CS-665 Software Designs & Patterns
 * Date: 04/22/2024
 * File Name: CSVToJsonTransformer.java
 * Description: This class implements the DataTransformer interface to convert CSV data into JSON format.
 * It parses CSV input using standard parsing techniques and produces a JSON output that represents the same data.
 */
public class CSVToJsonTransformer implements DataTransformer {

    /**
     * Transforms CSV data to JSON format.
     * @param inputData String representation of the input CSV data.
     * @return String representation of the JSON formatted data.
     */
    @Override
    public String transform(String inputData) {
        String[] lines = inputData.split("\n");
        JSONArray jsonArray = new JSONArray();

        if (lines.length > 1) {
            String[] headers = lines[0].split(",");

            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split(",");
                JSONObject jsonObject = new JSONObject();
                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j], values[j]);
                }
                jsonArray.put(jsonObject);
            }
        }

        return jsonArray.toString();
    }
}
