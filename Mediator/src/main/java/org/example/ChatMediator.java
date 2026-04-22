package org.example;


public interface ChatMediator {

    void registerClient(ChatClient client);


    void sendMessage(String message, ChatClient sender, String recipientName);


    void displayMessage(String message, ChatClient recipient);


    String[] getRegisteredClientNames();
}

