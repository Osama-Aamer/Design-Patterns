package org.example;

public class Game {

    public enum MapType { CITY, WILDERNESS }

    private static final int ROWS = 6;
    private static final int COLS = 10;

    /** * Factory method — creates either a CityMap or a WildernessMap. */
    public Map createMap(MapType type) {
        return switch (type) {
            case CITY       -> new CityMap(ROWS, COLS);
            case WILDERNESS -> new WildernessMap(ROWS, COLS);
        };
    }
}
