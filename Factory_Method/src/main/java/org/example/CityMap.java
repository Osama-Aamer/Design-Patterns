package org.example;

import java.util.Random;

//CityMap — tiles are randomly chosen from: Road (R), Forest (F), Building (B).

public class CityMap extends Map {
    private static final Random random = new Random();

    public CityMap(int rows, int cols) {
        super(rows, cols);
    }

    /** Factory method: returns a random city tile. */
    @Override
    public Tile createTile() {
        return switch (random.nextInt(3)) {
            case 0 -> new RoadTile();
            case 1 -> new ForestTile();
            default -> new BuildingTile();
        };
    }

    @Override
    public String getMapName() { return "City Map"; }

    @Override
    protected void printLegend() {
        System.out.println("\n  Legend:  R = Road   F = Forest   B = Building");
    }
}
