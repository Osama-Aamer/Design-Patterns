package org.example;

public abstract class Map {
    private final int rows;
    private final int cols;
    private final Tile[][] grid;

    public Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Tile[rows][cols];
        // Populate every cell using the factory method
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = createTile();   // factory method call
            }
        }
    }

    public abstract Tile createTile();

    public void display() {
        System.out.println("\n  " + getMapName() + " (" + rows + " x " + cols + ")");
        System.out.println("  " + "+---".repeat(cols) + "+");
        for (int r = 0; r < rows; r++) {
            System.out.print("  |");
            for (int c = 0; c < cols; c++) {
                System.out.print(" " + grid[r][c].getCharacter() + " |");
            }
            System.out.println();
            System.out.println("  " + "+---".repeat(cols) + "+");
        }
        printLegend();
    }

    /** Returns a human-readable map name, overridden in each subclass. */
    public abstract String getMapName();

    /** Prints the tile-character legend relevant to this map type. */
    protected abstract void printLegend();
}
