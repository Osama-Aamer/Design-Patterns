package org.example.pixelart.command;

import org.example.pixelart.PixelArtEditor;

public class MoveCursorUpCommand implements Command {
    private final PixelArtEditor editor;

    public MoveCursorUpCommand(PixelArtEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.moveUp();
    }
}

