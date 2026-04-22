package org.example;

import java.util.HashMap;
import java.util.Map;


public class ChatRoom implements ChatMediator {
    private Map<String, ChatClient> clients = new HashMap<>();

    @Override
    public void registerClient(ChatClient client) {
        clients.put(client.getUsername(), client);
        System.out.println(client.getUsername() + " joined the chat room");
    }

    @Override
    public void sendMessage(String message, ChatClient sender, String recipientName) {
        if (clients.containsKey(recipientName)) {
            ChatClient recipient = clients.get(recipientName);
            String formattedMessage = sender.getUsername() + ": " + message;
            recipient.receiveMessage(formattedMessage);
        } else {
            System.out.println("Recipient " + recipientName + " not found");
        }
    }

    @Override
    public void displayMessage(String message, ChatClient recipient) {
        recipient.receiveMessage(message);
    }

    @Override
    public String[] getRegisteredClientNames() {
        return clients.keySet().toArray(new String[0]);
    }
}
