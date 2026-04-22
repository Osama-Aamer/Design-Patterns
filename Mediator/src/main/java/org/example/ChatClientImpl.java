package org.example;

public class ChatClientImpl implements ChatClient {
    private String username;
    private ChatMediator mediator;
    private ChatClientUI ui;
    public ChatClientImpl(String username, ChatMediator mediator) {
        this.username = username;
        this.mediator = mediator;
        mediator.registerClient(this);
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public void receiveMessage(String message) {
        if (ui != null) {
            ui.displayReceivedMessage(message);
        }
    }
    @Override
    public void registerMediator(ChatMediator mediator) {
        this.mediator = mediator;
    }
    public void setUI(ChatClientUI ui) {
        this.ui = ui;
    }
    public void sendMessage(String message, String recipientName) {
        mediator.sendMessage(message, this, recipientName);
    }
    public String[] getAvailableRecipients() {
        return mediator.getRegisteredClientNames();
    }
}
