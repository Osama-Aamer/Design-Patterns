package org.example;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    private ChatMediator chatRoom = new ChatRoom();

    @Override
    public void start(Stage primaryStage) {
        createChatWindow("osama");
        createChatWindow("mohammad");
        createChatWindow("omar");
    }

    private void createChatWindow(String username) {
        Stage stage = new Stage();
        ChatClientImpl client = new ChatClientImpl(username, chatRoom);
        ChatClientUI ui = new ChatClientUI(client);
        ui.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

