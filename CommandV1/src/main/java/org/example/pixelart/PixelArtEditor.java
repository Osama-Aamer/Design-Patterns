package org.example.pixelart;

public class PixelArtEditor {
    public static final int GRID_SIZE = 8;
    private final boolean[][] pixels = new boolean[GRID_SIZE][GRID_SIZE];
    private int cursorRow;
    private int cursorCol;

    public int getGridSize() {
        return GRID_SIZE;
    }

    public int getCursorRow() {
        return cursorRow;
    }

    public int getCursorCol() {
        return cursorCol;
    }

    public boolean isPixelOn(int row, int col) {
        return pixels[row][col];
    }

    public void moveUp() {
        if (cursorRow > 0) {
            cursorRow--;
        }
    }

    public void moveDown() {
        if (cursorRow < GRID_SIZE - 1) {
            cursorRow++;
        }
    }

    public void moveLeft() {
        if (cursorCol > 0) {
            cursorCol--;
        }
    }

    public void moveRight() {
        if (cursorCol < GRID_SIZE - 1) {
            cursorCol++;
        }
    }

    public void togglePixelAtCursor() {
        pixels[cursorRow][cursorCol] = !pixels[cursorRow][cursorCol];
    }

    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("int[][] pixelArt = {\n");
        for (int row = 0; row < GRID_SIZE; row++) {
            builder.append("    {");
            for (int col = 0; col < GRID_SIZE; col++) {
                builder.append(pixels[row][col] ? 1 : 0);
                if (col < GRID_SIZE - 1) {
                    builder.append(", ");
                }
            }
            builder.append("}");
            if (row < GRID_SIZE - 1) {
                builder.append(",");
            }
            builder.append("\n");
        }
        builder.append("};");
        return builder.toString();
    }
}

