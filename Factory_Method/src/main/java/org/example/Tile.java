package org.example;

public interface Tile {
    /** Returns the single character that represents this tile (e.g. 'S', 'W'). */
    char getCharacter();

    /** Returns the full tile type name (e.g. "swamp", "water"). */
    String getType();

    /** Placeholder action, illustrates that tiles can have extra behaviour. */
    void action();
}
