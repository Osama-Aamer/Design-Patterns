package org.example;

public class BuildingTile implements Tile {
    @Override public char   getCharacter() { return 'B'; }
    @Override public String getType()      { return "building"; }
    @Override public void   action()       { System.out.println("You enter the building."); }
}
