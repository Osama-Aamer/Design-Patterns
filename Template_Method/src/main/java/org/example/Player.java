package org.example;

/**
 * Tracks the player's name, number of guesses taken, and whether they have won.
 */
public class Player {
    private final String name;
    private int guesses;
    private boolean won;

    public Player(String name) {
        this.name   = name;
        this.guesses = 0;
        this.won    = false;
    }

    public String  getName()    { return name; }
    public int     getGuesses() { return guesses; }
    public boolean hasWon()     { return won; }

    public void incrementGuesses()      { guesses++; }
    public void setWon(boolean won)     { this.won = won; }
}
