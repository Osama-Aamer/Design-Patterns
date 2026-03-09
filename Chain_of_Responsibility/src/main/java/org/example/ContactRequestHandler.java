package org.example;

/**
 * Determines the appropriate department based on keywords in the message
 * content and forwards the request accordingly.
 */
public class ContactRequestHandler extends FeedbackHandler {

    @Override
    public void handle(Message message) {
        if (message.getType() != MessageType.CONTACT_REQUEST) {
            super.handle(message);
            return;
        }

        System.out.println("  ContactRequestHandler: Processing contact request from <"
                + message.getSenderEmail() + ">.");

        String department = resolveDepartment(message.getContent());
        System.out.println("  ContactRequestHandler: Request forwarded to the "
                + department + " department. "
                + "They will contact " + message.getSenderEmail() + " shortly.");
    }

    /** Simple keyword-based department routing. */
    private String resolveDepartment(String content) {
        String lower = content.toLowerCase();
        if (lower.contains("invoice") || lower.contains("billing") || lower.contains("payment")) {
            return "Billing";
        } else if (lower.contains("technical") || lower.contains("bug") || lower.contains("error")) {
            return "Technical Support";
        } else if (lower.contains("delivery") || lower.contains("shipping")) {
            return "Logistics";
        } else {
            return "Customer Relations";
        }
    }
}

