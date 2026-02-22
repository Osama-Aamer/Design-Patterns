package org.example;

public class SwampTile implements Tile {
    @Override public char   getCharacter() { return 'S'; }
    @Override public String getType()      { return "swamp"; }
    @Override public void   action()       { System.out.println("You trudge through the murky swamp."); }
}
