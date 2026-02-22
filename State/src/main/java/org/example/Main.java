package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("   Game Character Development System    ");
        System.out.println("========================================");
        System.out.print("Enter your character's name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Hero";

        GameCharacter character = new GameCharacter(name);
        character.play();
    }
}