package org.example.pixelart.command;

import org.example.pixelart.PixelArtEditor;

public class MoveCursorDownCommand implements Command {
    private final PixelArtEditor editor;

    public MoveCursorDownCommand(PixelArtEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.moveDown();
    }
}

