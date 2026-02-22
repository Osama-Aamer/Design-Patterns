package org.example;

public class RoadTile implements Tile {
    @Override public char   getCharacter() { return 'R'; }
    @Override public String getType()      { return "road"; }
    @Override public void   action()       { System.out.println("You walk along the road."); }
}
