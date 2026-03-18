package org.example;

/**
 * If a handler cannot process the message it passes it down the chain
 * super.handle()
 */
public abstract class FeedbackHandler {

    private FeedbackHandler nextHandler;

    public void setNextHandler(FeedbackHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    //when they cannot process the given message
    public void handle(Message message) {
        if (nextHandler != null) {
            nextHandler.handle(message);
        } else {
            System.out.println("  [Chain end] No handler available for message type: "
                    + message.getType());
        }
    }
}

