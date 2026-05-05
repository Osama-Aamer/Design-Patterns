package org.example.pixelart.command;

import org.example.pixelart.PixelArtEditor;

public class MoveCursorRightCommand implements Command {
    private final PixelArtEditor editor;

    public MoveCursorRightCommand(PixelArtEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.moveRight();
    }
}

