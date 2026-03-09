package org.example;

/**
 * Reviews the claim content and either approves or rejects it.
 * A claim is approved when the content does not contain the word "unfounded";
 * otherwise it is rejected.
 */
public class CompensationHandler extends FeedbackHandler {

    @Override
    public void handle(Message message) {
        if (message.getType() != MessageType.COMPENSATION_CLAIM) {
            // Not our responsibility – pass down the chain.
            super.handle(message);
            return;
        }

        System.out.println("  CompensationHandler: Reviewing compensation claim from <"
                + message.getSenderEmail() + ">.");

        boolean approved = !message.getContent().toLowerCase().contains("unfounded");
        if (approved) {
            System.out.println("  CompensationHandler: Claim APPROVED. "
                    + "A confirmation will be sent to " + message.getSenderEmail() + ".");
        } else {
            System.out.println("  CompensationHandler: Claim REJECTED. "
                    + "Rejection notice sent to " + message.getSenderEmail() + ".");
        }
    }
}

