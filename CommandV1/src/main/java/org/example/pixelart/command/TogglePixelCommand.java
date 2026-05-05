package org.example.pixelart.command;

import org.example.pixelart.PixelArtEditor;

public class TogglePixelCommand implements Command {
    private final PixelArtEditor editor;

    public TogglePixelCommand(PixelArtEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.togglePixelAtCursor();
    }
}

