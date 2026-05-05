package org.example.pixelart.command;

import org.example.pixelart.PixelArtEditor;

public class MoveCursorLeftCommand implements Command {
    private final PixelArtEditor editor;

    public MoveCursorLeftCommand(PixelArtEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.moveLeft();
    }
}

