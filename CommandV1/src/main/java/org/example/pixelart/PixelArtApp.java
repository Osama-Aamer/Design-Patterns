package org.example.pixelart;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.pixelart.command.Command;
import org.example.pixelart.command.GenerateCodeCommand;
import org.example.pixelart.command.MoveCursorDownCommand;
import org.example.pixelart.command.MoveCursorLeftCommand;
import org.example.pixelart.command.MoveCursorRightCommand;
import org.example.pixelart.command.MoveCursorUpCommand;
import org.example.pixelart.command.TogglePixelCommand;

public class PixelArtApp extends Application {
    private static final int CELL_SIZE = 40;

    private PixelArtEditor editor;
    private Rectangle[][] cells;
    private Command moveUp;
    private Command moveDown;
    private Command moveLeft;
    private Command moveRight;
    private Command togglePixel;
    private Command generateCode;

    @Override
    public void start(Stage stage) {
        editor = new PixelArtEditor();
        cells = new Rectangle[editor.getGridSize()][editor.getGridSize()];

        moveUp = new MoveCursorUpCommand(editor);
        moveDown = new MoveCursorDownCommand(editor);
        moveLeft = new MoveCursorLeftCommand(editor);
        moveRight = new MoveCursorRightCommand(editor);
        togglePixel = new TogglePixelCommand(editor);
        generateCode = new GenerateCodeCommand(editor, System.out::println);

        GridPane gridPane = buildGrid();
        Button generateButton = new Button("Create Code");
        generateButton.setOnAction(event -> generateCode.execute());

        Label helpText = new Label("Arrow keys move, Space toggles pixel.");
        HBox controls = new HBox(10, generateButton);
        controls.setAlignment(Pos.CENTER);

        VBox layout = new VBox(12, helpText, gridPane, controls);
        layout.setPadding(new Insets(12));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.setOnKeyPressed(event -> handleKey(event.getCode()));

        stage.setTitle("Pixel Art Editor");
        stage.setScene(scene);
        stage.show();

        render();
        gridPane.requestFocus();
    }

    private GridPane buildGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setFocusTraversable(true);
        gridPane.setOnMouseClicked(event -> gridPane.requestFocus());

        for (int row = 0; row < editor.getGridSize(); row++) {
            for (int col = 0; col < editor.getGridSize(); col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.LIGHTGRAY);
                cells[row][col] = cell;
                gridPane.add(cell, col, row);
            }
        }

        return gridPane;
    }

    private void handleKey(KeyCode code) {
        if (code == KeyCode.UP) {
            moveUp.execute();
        } else if (code == KeyCode.DOWN) {
            moveDown.execute();
        } else if (code == KeyCode.LEFT) {
            moveLeft.execute();
        } else if (code == KeyCode.RIGHT) {
            moveRight.execute();
        } else if (code == KeyCode.SPACE) {
            togglePixel.execute();
        } else {
            return;
        }

        render();
    }

    private void render() {
        for (int row = 0; row < editor.getGridSize(); row++) {
            for (int col = 0; col < editor.getGridSize(); col++) {
                Rectangle cell = cells[row][col];
                boolean isOn = editor.isPixelOn(row, col);
                cell.setFill(isOn ? Color.BLACK : Color.WHITE);
                if (row == editor.getCursorRow() && col == editor.getCursorCol()) {
                    cell.setStroke(Color.RED);
                    cell.setStrokeWidth(2);
                } else {
                    cell.setStroke(Color.LIGHTGRAY);
                    cell.setStrokeWidth(1);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
