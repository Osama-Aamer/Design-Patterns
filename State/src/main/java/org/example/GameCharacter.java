package org.example;

import java.util.Scanner;

public class GameCharacter {
    private static final Scanner scanner = new Scanner(System.in);

    private final String name;
    private int experiencePoints;
    private int healthPoints;
    private CharacterState state;
    private boolean gameOver = false;

    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.healthPoints = 100;
        this.state = new NoviceState(this);
    }

    // ── Game loop ────────────────────────────────────────────────────────────

    public void play() {
        System.out.println("\n========================================");
        System.out.println("  Welcome, " + name + "! Your adventure begins.");
        System.out.println("========================================");

        while (!gameOver) {
            printStatus();
            state.action();
        }

        System.out.println("\n🏆  Congratulations, " + name + "! You have reached MASTER level.");
        System.out.println("    Your journey is complete. Final stats:");
        printStatus();
    }

    // ── Status display ───────────────────────────────────────────────────────

    public void printStatus() {
        System.out.println("\n----------------------------------------");
        System.out.printf("  Character : %s%n", name);
        System.out.printf("  Level     : %s%n", state.getLevelName());
        System.out.printf("  XP        : %d%n", experiencePoints);
        System.out.printf("  HP        : %d%n", healthPoints);
        System.out.println("----------------------------------------");
    }

    // ── State management ────────────────────────────────────────────────────

    public void setState(CharacterState state) {
        this.state = state;
    }

    public void setGameOver() {
        this.gameOver = true;
    }

    // ── Attribute manipulation ───────────────────────────────────────────────

    public void addExperience(int xp) {
        experiencePoints += xp;
        System.out.printf("  +%d XP  (total: %d)%n", xp, experiencePoints);
    }

    public void addHealth(int hp) {
        healthPoints = Math.min(healthPoints + hp, 100);
        System.out.printf("  +%d HP  (total: %d)%n", hp, healthPoints);
    }

    public void reduceHealth(int hp) {
        healthPoints = Math.max(healthPoints - hp, 0);
        System.out.printf("  -%d HP  (total: %d)%n", hp, healthPoints);
    }

    public int getExperiencePoints() { return experiencePoints; }
    public int getHealthPoints()     { return healthPoints; }
    public String getName()          { return name; }

    // ── User input ───────────────────────────────────────────────────────────

    public int readUserChoice(String[] options) {
        System.out.println("\n  What do you want to do?");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("  %d. %s%n", i + 1, options[i]);
        }
        System.out.print("  > ");
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= options.length) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {}
            System.out.print("  Invalid choice. Try again > ");
        }
    }
}
