package org.example;

/**
 * Abstract base class for all feedback handlers.
 *
 * Each concrete handler is responsible for one MessageType.
 * If a handler cannot process the message it passes it down the chain
 * super.handle(), mirrors the Chain of Responsibility pattern.
 */
public abstract class FeedbackHandler {

    private FeedbackHandler nextHandler;

    // Links this handler to the next one in the chain.
    public void setNextHandler(FeedbackHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * when they cannot process the given message type so that the request
     * travels further down the chain.
     */
    public void handle(Message message) {
        if (nextHandler != null) {
            nextHandler.handle(message);
        } else {
            System.out.println("  [Chain end] No handler available for message type: "
                    + message.getType());
        }
    }
}

