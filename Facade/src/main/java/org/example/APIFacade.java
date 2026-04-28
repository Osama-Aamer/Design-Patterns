package org.example;

import java.io.IOException;

public class APIFacade {
    private final APIHttpClient httpClient;
    private final APIJsonParser jsonParser;

    public APIFacade() {
        this.httpClient = new APIHttpClient();
        this.jsonParser = new APIJsonParser();
    }

    public String getAttributeValueFromJson(String urlString, String attributeName)
            throws IllegalArgumentException, IOException {
        if (urlString == null || urlString.isBlank()) {
            throw new IllegalArgumentException("URL must not be blank");
        }

        String json = httpClient.getJson(urlString);
        return jsonParser.findAttributeValue(json, attributeName);
    }
}

