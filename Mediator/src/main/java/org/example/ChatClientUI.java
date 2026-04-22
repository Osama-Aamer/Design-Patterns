package org.example;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClientUI {
    private ChatClientImpl client;
    private TextArea messageArea;
    private TextField messageField;
    private ComboBox<String> recipientCombo;
    private Button sendButton;
    private Scene scene;
    public ChatClientUI(ChatClientImpl client) {
        this.client = client;
        client.setUI(this);
        createUI();
    }
    private void createUI() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-padding: 10;");
        // Create top section with title
        Label titleLabel = new Label("Messages:");
        titleLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        VBox topBox = new VBox(titleLabel);
        topBox.setPadding(new Insets(0, 0, 10, 0));
        root.setTop(topBox);
        // Create center section - message display area
        messageArea = new TextArea();
        messageArea.setEditable(false);
        messageArea.setWrapText(true);
        messageArea.setPrefRowCount(15);
        messageArea.setStyle("-fx-control-inner-background: #f0f0f0;");
        ScrollPane scrollPane = new ScrollPane(messageArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        root.setCenter(scrollPane);
        // Create bottom section - input controls
        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(10, 0, 0, 0));
         // Recipient selection
         HBox recipientBox = new HBox(10);
         Label recipientLabel = new Label("Send to:");
         recipientCombo = new ComboBox<>();
         recipientCombo.setPrefWidth(150);
         updateRecipientList();
         // Update recipient list whenever the dropdown is shown
         recipientCombo.setOnShowing(e -> updateRecipientList());
         recipientBox.getChildren().addAll(recipientLabel, recipientCombo);
        // Message input
        HBox messageInputBox = new HBox(10);
        Label messageLabel = new Label("Message:");
        messageField = new TextField();
        messageField.setPromptText("Enter message...");
        HBox.setHgrow(messageField, Priority.ALWAYS);
        sendButton = new Button("Send");
        sendButton.setStyle("-fx-padding: 5px 20px;");
        sendButton.setOnAction(e -> handleSendMessage());
        messageField.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                handleSendMessage();
            }
        });
        messageInputBox.getChildren().addAll(messageLabel, messageField, sendButton);
        bottomBox.getChildren().addAll(recipientBox, messageInputBox);
        root.setBottom(bottomBox);
        scene = new Scene(root, 600, 500);
    }
    public void show(Stage stage) {
        stage.setTitle(client.getUsername());
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();
    }
    public void displayReceivedMessage(String message) {
        messageArea.appendText(message + "\n");
    }
     private void handleSendMessage() {
         // Refresh recipient list before sending to ensure all clients are available
         updateRecipientList();

         String message = messageField.getText().trim();
         String recipient = recipientCombo.getValue();
         if (message.isEmpty()) {
             showAlert("Error", "Message cannot be empty!");
             return;
         }
         if (recipient == null || recipient.isEmpty()) {
             showAlert("Error", "Please select a recipient!");
             return;
         }
         if (recipient.equals(client.getUsername())) {
             showAlert("Error", "You cannot send a message to yourself!");
             return;
         }
         client.sendMessage(message, recipient);
         messageArea.appendText("You to " + recipient + ": " + message + "\n");
         messageField.clear();
     }
    private void updateRecipientList() {
        String[] recipients = client.getAvailableRecipients();
        recipientCombo.getItems().clear();
        for (String recipient : recipients) {
            if (!recipient.equals(client.getUsername())) {
                recipientCombo.getItems().add(recipient);
            }
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
