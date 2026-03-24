package memento.guistate.view;

import memento.guistate.controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Gui extends Application {

    private Controller controller;
    private ColorBox colorBox1;
    private ColorBox colorBox2;
    private ColorBox colorBox3;
    private CheckBox checkBox;
    private HistoryWindow historyWindow;

    @Override
    public void start(Stage stage) {

        controller = new Controller(this);
        historyWindow = new HistoryWindow(controller);

        Insets insets = new Insets(10, 10, 10, 10);

        colorBox1 = new ColorBox(1, controller);
        colorBox2 = new ColorBox(2, controller);
        colorBox3 = new ColorBox(3, controller);

        checkBox = new CheckBox("Click me!");
        checkBox.setPadding(insets);
        checkBox.setOnAction(event -> controller.setIsSelected(checkBox.isSelected()));

        HBox hBox = new HBox(colorBox1.getRectangle(), colorBox2.getRectangle(), colorBox3.getRectangle());
        hBox.setSpacing(10);
        HBox.setMargin(colorBox1.getRectangle(), insets);
        HBox.setMargin(colorBox2.getRectangle(), insets);
        HBox.setMargin(colorBox3.getRectangle(), insets);

        Label label = new Label("Ctrl+Z = Undo   |   Ctrl+Y = Redo");
        label.setPadding(insets);

        Button historyButton = new Button("Show History");
        historyButton.setPadding(new Insets(6, 12, 6, 12));
        historyButton.setOnAction(event -> historyWindow.show());
        HBox buttonRow = new HBox(historyButton);
        buttonRow.setPadding(new Insets(0, 10, 10, 10));

        VBox vBox = new VBox(hBox, checkBox, label, buttonRow);

        Scene scene = new Scene(vBox);
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown()) {
                if (event.getCode() == KeyCode.Z) {
                    System.out.println("Ctrl+Z pressed");
                    controller.undo();
                    historyWindow.refresh();
                } else if (event.getCode() == KeyCode.Y) {
                    System.out.println("Ctrl+Y pressed");
                    controller.redo();
                    historyWindow.refresh();
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("Memento Pattern — Undo/Redo/History");
        stage.show();
    }

    /** Called after any state restore to keep the GUI in sync with the model. */
    public void updateGui() {
        colorBox1.setColor(controller.getOption(1));
        colorBox2.setColor(controller.getOption(2));
        colorBox3.setColor(controller.getOption(3));
        checkBox.setSelected(controller.getIsSelected());
        historyWindow.refresh();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
