package org.example;

public abstract class Game {

    public final void play(int numberOfPlayers) {
        // template method – defines the skeleton of the game
        initializeGame(numberOfPlayers);
        int playerInTurn = 0;
        while (!endOfGame()) {
            playSingleTurn(playerInTurn);
            playerInTurn = ++playerInTurn % numberOfPlayers;
        }
        displayWinner();
    }

    public abstract void initializeGame(int numberOfPlayers);
    public abstract boolean endOfGame();
    public abstract void playSingleTurn(int player);
    public abstract void displayWinner();
}
