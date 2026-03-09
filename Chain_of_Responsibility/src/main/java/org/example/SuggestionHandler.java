package org.example;

/**
 * Logs the suggestion and assigns a priority based on keywords found in
 * the message content.
 */
public class SuggestionHandler extends FeedbackHandler {

    @Override
    public void handle(Message message) {
        if (message.getType() != MessageType.DEVELOPMENT_SUGGESTION) {
            super.handle(message);
            return;
        }

        System.out.println("  SuggestionHandler: Logging development suggestion from <"
                + message.getSenderEmail() + ">.");

        String priority = resolvePriority(message.getContent());
        System.out.println("  SuggestionHandler: Suggestion logged with priority ["
                + priority + "]. It will be reviewed in the next planning session.");
    }

    /** Assigns a priority label based on urgency keywords in the content. */
    private String resolvePriority(String content) {
        String lower = content.toLowerCase();
        if (lower.contains("critical") || lower.contains("urgent") || lower.contains("immediately")) {
            return "HIGH";
        } else if (lower.contains("important") || lower.contains("asap") || lower.contains("soon")) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }
}

