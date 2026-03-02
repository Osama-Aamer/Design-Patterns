package org.example;

public class Main {
    public static void main(String[] args) {
        // Use the Game framework via the Template Method pattern.

        Game game = new NumberGuessingGame();
        game.play(2);   // 2 players playing currently
    }
}
//commit pls
//note:  we can Swap NumberGuessingGame for any other Game subclass to play a different game.