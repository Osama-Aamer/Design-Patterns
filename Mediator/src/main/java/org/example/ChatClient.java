package org.example;


public interface ChatClient {
    String getUsername();
    void receiveMessage(String message);
    void registerMediator(ChatMediator mediator);
}
