package memento.guistate.view;

import memento.guistate.controller.Controller;
import memento.guistate.model.IMemento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;


public class HistoryWindow {

    private final Controller controller;
    private final Stage stage;
    private final ListView<String> listView;

    public HistoryWindow(Controller controller) {
        this.controller = controller;

        listView = new ListView<>();
        listView.setPrefHeight(300);
        listView.setPrefWidth(420);

        listView.setOnMouseClicked(event -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                controller.restoreFromHistory(selectedIndex);
            }
        });

        Label titleLabel = new Label("Click a state to restore it:");
        titleLabel.setPadding(new Insets(8, 8, 4, 8));

        VBox vBox = new VBox(titleLabel, listView);
        Scene scene = new Scene(vBox);

        stage = new Stage();
        stage.setTitle("State History");
        stage.setScene(scene);
    }

    public void show() {
        refresh();
        stage.show();
        stage.toFront();
    }

    public void refresh() {
        List<IMemento> history = controller.getHistory();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < history.size(); i++) {
            items.add((i + 1) + ".  " + history.get(i).getDescription());
        }
        listView.setItems(items);
    }
}
