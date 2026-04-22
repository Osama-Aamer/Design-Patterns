package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the Chat Application using Mediator Pattern
 *
 * To run this application:
 * 1. Using Maven: mvn javafx:run
 * 2. Using IDE: Right-click and Run as Java Application
 * 3. Command line: java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml org.example.Main
 */
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

