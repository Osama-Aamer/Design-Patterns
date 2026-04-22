package org.example;

import java.util.HashMap;
import java.util.Map;


public class RecommendationRegistry {
    private Map<String, Recommendation> registry = new HashMap<>();

    public void registerRecommendation(String name, Recommendation recommendation) {
        registry.put(name, recommendation);
    }

    public Recommendation getRecommendation(String name) {
        return registry.get(name);
    }

    public Recommendation cloneRecommendation(String name) {
        Recommendation recommendation = registry.get(name);
        if (recommendation == null) {
            return null;
        }
        return recommendation.clone();
    }

    public boolean hasRecommendation(String name) {
        return registry.containsKey(name);
    }

    public void listAllRecommendations() {
        if (registry.isEmpty()) {
            System.out.println("No recommendations in the registry.\n");
        } else {
            System.out.println("=== Available Recommendations ===");
            for (String name : registry.keySet()) {
                Recommendation rec = registry.get(name);
                System.out.println("- " + name + " (" + rec.getTargetAudience() + 
                                   ", " + rec.getBookCount() + " books)");
            }
            System.out.println();
        }
    }
}
