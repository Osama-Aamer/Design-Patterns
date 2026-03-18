package org.example;

import java.util.List;

/**
 * Chain order:
 *   CompensationHandler → ContactRequestHandler → SuggestionHandler → GeneralFeedbackHandler
 */
public class Main {

    public static void main(String[] args) {

        // linking the chain
        FeedbackHandler compensationHandler  = new CompensationHandler();
        FeedbackHandler contactHandler       = new ContactRequestHandler();
        FeedbackHandler suggestionHandler    = new SuggestionHandler();
        FeedbackHandler generalHandler       = new GeneralFeedbackHandler();

        compensationHandler.setNextHandler(contactHandler);
        contactHandler.setNextHandler(suggestionHandler);
        suggestionHandler.setNextHandler(generalHandler);

        FeedbackHandler chain = compensationHandler; // entry point

        // sample messages to process
        List<Message> messages = List.of(
            new Message(
                MessageType.COMPENSATION_CLAIM,
                "My order arrived damaged. I would like to claim compensation.",
                "alice@example.com"
            ),
            new Message(
                MessageType.COMPENSATION_CLAIM,
                "This claim is unfounded and I just want to cause trouble.",
                "troll@example.com"
            ),
            new Message(
                MessageType.CONTACT_REQUEST,
                "I have a question about my invoice and billing details.",
                "bob@example.com"
            ),
            new Message(
                MessageType.CONTACT_REQUEST,
                "There is a technical bug in your mobile app that causes errors.",
                "carol@example.com"
            ),
            new Message(
                MessageType.DEVELOPMENT_SUGGESTION,
                "It would be great to have a dark mode. Not urgent but would be nice.",
                "dave@example.com"
            ),
            new Message(
                MessageType.DEVELOPMENT_SUGGESTION,
                "The checkout process is broken — this is critical and needs fixing immediately!",
                "eve@example.com"
            ),
            new Message(
                MessageType.GENERAL_FEEDBACK,
                "I love your service, it is excellent and I am very happy with everything!",
                "frank@example.com"
            ),
            new Message(
                MessageType.GENERAL_FEEDBACK,
                "The delivery was terrible and the product quality was awful.",
                "grace@example.com"
            )
        );

        // - Route each message through the chain --
        System.out.println("=== Customer Feedback Handler ===\n");
        for (Message msg : messages) {
            System.out.println("Incoming: " + msg);
            chain.handle(msg);
            System.out.println();
        }
    }
}