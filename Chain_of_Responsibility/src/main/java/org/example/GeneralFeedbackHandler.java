package org.example;

/**
 * Analyses the sentiment of the message (positive / negative / neutral)
 * and sends an appropriate response. This is the last handler in the chain.
 */
public class GeneralFeedbackHandler extends FeedbackHandler {

    @Override
    public void handle(Message message) {
        if (message.getType() != MessageType.GENERAL_FEEDBACK) {
            super.handle(message);
            return;
        }

        System.out.println("  GeneralFeedbackHandler: Analysing feedback from <"
                + message.getSenderEmail() + ">.");

        String sentiment = analyseSentiment(message.getContent());
        System.out.println("  GeneralFeedbackHandler: Sentiment detected – " + sentiment + ".");
        System.out.println("  GeneralFeedbackHandler: Thank-you response sent to "
                + message.getSenderEmail() + ".");
    }

    /** Very simple keyword-based sentiment analysis. */
    private String analyseSentiment(String content) {
        String lower = content.toLowerCase();
        long positiveScore = countKeywords(lower,
                "great", "excellent", "love", "amazing", "happy", "good", "thank");
        long negativeScore = countKeywords(lower,
                "bad", "terrible", "hate", "awful", "disappointed", "poor", "worst");

        if (positiveScore > negativeScore) return "POSITIVE";
        if (negativeScore > positiveScore) return "NEGATIVE";
        return "NEUTRAL";
    }

    private long countKeywords(String text, String... keywords) {
        long count = 0;
        for (String kw : keywords) {
            if (text.contains(kw)) count++;
        }
        return count;
    }
}

