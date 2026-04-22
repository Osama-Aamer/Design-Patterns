package org.example;


public class MediatorPatternDemo {


    public static void demonstrateMediator() {
        System.out.println("=== MEDIATOR PATTERN DEMONSTRATION ===\n");

        System.out.println("STEP 1: Creating ChatRoom (Concrete Mediator)");
        ChatMediator chatRoom = new ChatRoom();
        System.out.println("✓ ChatRoom created\n");

        System.out.println("STEP 2: Creating Chat Clients (Concrete Colleagues)");
        ChatClientImpl alice = new ChatClientImpl("Alice", chatRoom);
        ChatClientImpl bob = new ChatClientImpl("Bob", chatRoom);
        ChatClientImpl charlie = new ChatClientImpl("Charlie", chatRoom);
        System.out.println("✓ Three clients created and registered\n");

        System.out.println("STEP 3: Sending Messages (Mediator routes them)");
        System.out.println("---");
        System.out.println("Alice sends: 'Hello Bob, how are you?'");
        alice.sendMessage("Hello Bob, how are you?", "Bob");
        System.out.println();

        System.out.println("Bob sends: 'I'm great, thanks for asking!'");
        bob.sendMessage("I'm great, thanks for asking!", "Alice");
        System.out.println();

        System.out.println("Charlie sends: 'Hi everyone!'");
        charlie.sendMessage("Hi everyone!", "Alice");
        System.out.println();

        System.out.println("STEP 4: Registered Clients in ChatRoom");
        System.out.println("---");
        String[] registeredClients = chatRoom.getRegisteredClientNames();
        System.out.println("Total clients registered: " + registeredClients.length);
        for (String clientName : registeredClients) {
            System.out.println("  • " + clientName);
        }
        System.out.println();

        System.out.println("STEP 5: Error Handling - Non-existent Recipient");
        System.out.println("---");
        System.out.println("Alice sends to 'NonExistent': 'Does this work?'");
        alice.sendMessage("Does this work?", "NonExistent");
        System.out.println();

        System.out.println("=== KEY INSIGHTS ===");
        System.out.println("✓ Clients never directly reference each other");
        System.out.println("✓ All communication goes through ChatRoom mediator");
        System.out.println("✓ ChatRoom controls the message routing");
        System.out.println("✓ New clients can be added without modifying existing ones");
        System.out.println("✓ Loose coupling achieved!\n");
    }

    /**
     * Main entry point for demo
     */
    public static void main(String[] args) {
        demonstrateMediator();
    }
}

