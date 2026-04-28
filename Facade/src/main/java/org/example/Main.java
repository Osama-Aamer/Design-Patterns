package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        APIFacade facade = new APIFacade();

        try {
            String joke = facade.getAttributeValueFromJson(
                    "https://api.chucknorris.io/jokes/random",
                    "value"
            );
            System.out.println("Chuck Norris joke:");
            System.out.println(joke);

            String base = facade.getAttributeValueFromJson(
                    "https://api.fxratesapi.com/latest",
                    "base"
            );
            System.out.println("\nFX Rates base:");
            System.out.println(base);
        } catch (IOException e) {
            System.out.println("API request failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid response: " + e.getMessage());
        }
    }
}