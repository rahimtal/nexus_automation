package com.NexustAPIAutomation.java;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for comparing JSON responses while ignoring dynamic fields.
 * Useful for tests where certain fields (like hostnames, timestamps, IDs) change between runs.
 * 
 * Usage:
 *   JsonComparator.assertEquals(actual, expected, "URL");  // Ignore URL field
 *   JsonComparator.assertEquals(actual, expected);         // Exact match
 */
public class JsonComparator {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Compare two JSON strings, ignoring specified fields
     * @param actual The actual JSON response from API
     * @param expected The expected JSON template
     * @param fieldsToIgnore Variable list of field names to ignore in comparison
     */
    public static void assertEquals(String actual, String expected, String... fieldsToIgnore) {
        try {
            JsonNode actualNode = mapper.readTree(actual);
            JsonNode expectedNode = mapper.readTree(expected);
            
            if (fieldsToIgnore != null && fieldsToIgnore.length > 0) {
                actualNode = stripFields(actualNode, fieldsToIgnore);
                expectedNode = stripFields(expectedNode, fieldsToIgnore);
            }
            
            if (!actualNode.equals(expectedNode)) {
                String message = String.format(
                    "JSON mismatch!\nExpected:\n%s\n\nActual:\n%s",
                    expectedNode.toPrettyString(),
                    actualNode.toPrettyString()
                );
                throw new AssertionError(message);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to compare JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Compare JSON while normalizing dynamic hostnames in URLs
     * Useful for cogsDrillback URLs that contain container IDs
     * @param actual The actual JSON response
     * @param expected The expected JSON template
     */
    public static void assertEqualsIgnoreHostnames(String actual, String expected) {
        try {
            JsonNode actualNode = mapper.readTree(actual);
            JsonNode expectedNode = mapper.readTree(expected);
            
            // Normalize all URLs to remove hostnames
            actualNode = normalizeUrls(actualNode);
            expectedNode = normalizeUrls(expectedNode);
            
            if (!actualNode.equals(expectedNode)) {
                String message = String.format(
                    "JSON mismatch (hostnames normalized)!\nExpected:\n%s\n\nActual:\n%s",
                    expectedNode.toPrettyString(),
                    actualNode.toPrettyString()
                );
                throw new AssertionError(message);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to compare JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Strip specified fields from JSON tree recursively
     */
    private static JsonNode stripFields(JsonNode node, String... fields) {
        Set<String> fieldsToRemove = new HashSet<>();
        for (String field : fields) {
            fieldsToRemove.add(field);
        }
        
        if (node.isObject()) {
            ObjectNode obj = (ObjectNode) node;
            for (String field : fieldsToRemove) {
                obj.remove(field);
            }
            
            // Recursively process nested objects and arrays
            node.fields().forEachRemaining(entry -> {
                JsonNode value = entry.getValue();
                if (value.isObject() || value.isArray()) {
                    obj.set(entry.getKey(), stripFields(value, fields));
                }
            });
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                JsonNode item = node.get(i);
                if (item.isObject() || item.isArray()) {
                    ((com.fasterxml.jackson.databind.node.ArrayNode) node).set(i, stripFields(item, fields));
                }
            }
        }
        
        return node;
    }

    /**
     * Normalize all URL fields by removing hostnames (containers, servers)
     * Converts: cogsDrillback://DGPB/?Db=&Srv=4669f4bd4959&...
     * To:      cogsDrillback://DGPB/?Db=&Srv=NORMALIZED&...
     */
    private static JsonNode normalizeUrls(JsonNode node) {
        if (node.isObject()) {
            ObjectNode obj = (ObjectNode) node;
            
            // Check for URL-related fields and normalize them
            node.fields().forEachRemaining(entry -> {
                if (entry.getValue().isTextual()) {
                    String fieldName = entry.getKey();
                    String value = entry.getValue().asText();
                    
                    // Normalize if field contains URL-like names or if value contains cogsDrillback
                    if (fieldName.toLowerCase().contains("url") || 
                        fieldName.toLowerCase().contains("link") ||
                        value.contains("cogsDrillback://") ||
                        value.contains("Srv=")) {
                        String normalized = normalizeUrl(value);
                        obj.put(fieldName, normalized);
                    }
                } else if (entry.getValue().isObject() || entry.getValue().isArray()) {
                    obj.set(entry.getKey(), normalizeUrls(entry.getValue()));
                }
            });
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                JsonNode item = node.get(i);
                if (item.isObject() || item.isArray()) {
                    ((com.fasterxml.jackson.databind.node.ArrayNode) node).set(i, normalizeUrls(item));
                }
            }
        }
        
        return node;
    }

    /**
     * Normalize a single URL by replacing hostnames with a placeholder
     */
    private static String normalizeUrl(String url) {
        // Replace any hostname-like pattern in cogsDrillback URLs with placeholder
        // Pattern: Srv=<hostname> where hostname is alphanumeric
        return url.replaceAll("(Srv=)[^&]+", "$1NORMALIZED");
    }

    /**
     * Pretty print JSON for debugging
     */
    public static String prettyPrint(String json) {
        try {
            JsonNode node = mapper.readTree(json);
            return node.toPrettyString();
        } catch (Exception e) {
            return json;
        }
    }
}
