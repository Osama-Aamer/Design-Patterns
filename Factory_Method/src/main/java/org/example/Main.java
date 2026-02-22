package org.example;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        // Generate and display a Wilderness map
        Map wildernessMap = game.createMap(Game.MapType.WILDERNESS);
        wildernessMap.display();

        System.out.println();

        // Generate and display a City map
        Map cityMap = game.createMap(Game.MapType.CITY);
        cityMap.display();
    }
}