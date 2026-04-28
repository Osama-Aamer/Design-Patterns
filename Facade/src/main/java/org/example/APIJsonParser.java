package org.example;

import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APIJsonParser {
    public String findAttributeValue(String json, String attributeName) {
        if (attributeName == null || attributeName.isBlank()) {
            throw new IllegalArgumentException("Attribute name must not be blank");
        }

        Object parsed;
        try {
            parsed = new JSONParser().parse(json);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid JSON response", e);
        }

        Object value = findInJson(parsed, attributeName);
        if (value == null) {
            throw new IllegalArgumentException("Attribute not found: " + attributeName);
        }
        return String.valueOf(value);
    }

    private Object findInJson(Object node, String attributeName) {
        if (node instanceof JSONObject jsonObject) {
            for (Iterator<Map.Entry<?, ?>> it = jsonObject.entrySet().iterator(); it.hasNext();) {
                Map.Entry<?, ?> entry = it.next();
                if (attributeName.equals(String.valueOf(entry.getKey()))) {
                    return entry.getValue();
                }
                Object nested = findInJson(entry.getValue(), attributeName);
                if (nested != null) {
                    return nested;
                }
            }
        }

        if (node instanceof JSONArray jsonArray) {
            for (Object item : jsonArray) {
                Object nested = findInJson(item, attributeName);
                if (nested != null) {
                    return nested;
                }
            }
        }

        return null;
    }
}

