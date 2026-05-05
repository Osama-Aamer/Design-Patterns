package org.example.pixelart.command;

import java.util.function.Consumer;
import org.example.pixelart.PixelArtEditor;

public class GenerateCodeCommand implements Command {
    private final PixelArtEditor editor;
    private final Consumer<String> output;

    public GenerateCodeCommand(PixelArtEditor editor, Consumer<String> output) {
        this.editor = editor;
        this.output = output;
    }

    @Override
    public void execute() {
        output.accept(editor.generateJavaCode());
    }
}

