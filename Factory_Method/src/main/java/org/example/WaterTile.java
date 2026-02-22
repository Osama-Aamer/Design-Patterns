package org.example;

public class WaterTile implements Tile {
    @Override public char   getCharacter() { return 'W'; }
    @Override public String getType()      { return "water"; }
    @Override public void   action()       { System.out.println("You swim across the water."); }
}
