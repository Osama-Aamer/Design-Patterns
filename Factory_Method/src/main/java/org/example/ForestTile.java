package org.example;

public class ForestTile implements Tile {
    @Override public char   getCharacter() { return 'F'; }
    @Override public String getType()      { return "forest"; }
    @Override public void   action()       { System.out.println("You wander through the dense forest."); }
}
