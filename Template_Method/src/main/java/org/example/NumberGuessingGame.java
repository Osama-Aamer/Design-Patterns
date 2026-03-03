package org.example;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame extends Game {

    private static final int MIN          = 1;
    private static final int MAX          = 100;
    private static final int MAX_ATTEMPTS = 10;   // per player

    private final Scanner scanner = new Scanner(System.in);
    private final Random  random  = new Random();

    private int      secretNumber;
    private Player[] players;
    private int      winnerIndex = -1;   // -1 = no winner yet

    // ------------------------------------------------------------------ //
    //  Template-method hooks                                               //
    // ------------------------------------------------------------------ //

    @Override
    public void initializeGame(int numberOfPlayers) {
        secretNumber = random.nextInt(MAX - MIN + 1) + MIN;
        players      = new Player[numberOfPlayers];

        System.out.println("===========================================");
        System.out.println("   Welcome to the Number Guessing Game!   ");
        System.out.println("===========================================");
        System.out.println("A secret number between " + MIN + " and " + MAX + " has been chosen.");
        System.out.println("Each player has " + MAX_ATTEMPTS + " attempts. Good luck!\n");

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) name = "Player " + (i + 1);
            players[i] = new Player(name);
        }
        System.out.println();
    }

    @Override
    public boolean endOfGame() {
        // End when someone has won
        if (winnerIndex != -1) return true;

        // End when every player has used all their attempts
        for (Player p : players) {
            if (p.getGuesses() < MAX_ATTEMPTS) {
                return false;   // at least one player still has attempts left
            }
        }
        return true;
    }

    @Override
    public void playSingleTurn(int playerIndex) {
        Player player = players[playerIndex];

        // Skip players who already won or exhausted their attempts
        if (player.hasWon() || player.getGuesses() >= MAX_ATTEMPTS) return;

        int attemptsLeft = MAX_ATTEMPTS - player.getGuesses();
        System.out.println("--- " + player.getName() + "'s turn  "
                + "(attempts left: " + attemptsLeft + ") ---");
        System.out.print("Enter your guess (" + MIN + "-" + MAX + "): ");

        int guess = readInt();
        player.incrementGuesses();

        if (guess < MIN || guess > MAX) {
            System.out.println("  [!] Out of range — guess must be between "
                    + MIN + " and " + MAX + ". Turn wasted!\n");
            return;
        }

        if (guess == secretNumber) {
            System.out.println("  *** Correct! Well done, " + player.getName() + "! ***\n");
            player.setWon(true);
            winnerIndex = playerIndex;
        } else if (guess < secretNumber) {
            System.out.println("  Too low — try higher.\n");
        } else {
            System.out.println("  Too high — try lower.\n");
        }
    }

    @Override
    public void displayWinner() {
        System.out.println("===========================================");
        if (winnerIndex != -1) {
            Player winner = players[winnerIndex];
            System.out.println("  Game over!  " + winner.getName()
                    + " wins with " + winner.getGuesses() + " guess(es)!");
        } else {
            System.out.println("  Game over!  Nobody guessed the number.");
            System.out.println("  The secret number was: " + secretNumber);
        }
        System.out.println("-------------------------------------------");
        System.out.println("  Final scores:");
        for (Player p : players) {
            System.out.printf("    %-15s  %2d guess(es)  %s%n",
                    p.getName(),
                    p.getGuesses(),
                    p.hasWon() ? "*** WINNER ***" : "");
        }
        System.out.println("===========================================");
    }

    // --------------- //
    //  Helper                                                              //
    // --------------//

    /** Reads an integer from stdin; re-prompts on invalid input. */
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  [!] Please enter a valid number: ");
            }
        }
    }
}
