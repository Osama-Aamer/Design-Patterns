package org.example;


public class MediatorPatternDemo {


    public static void demonstrateMediator() {
        System.out.println("=== MEDIATOR PATTERN DEMONSTRATION ===\n");

        System.out.println("STEP 1: Creating ChatRoom (Concrete Mediator)");
        ChatMediator chatRoom = new ChatRoom();
        System.out.println("✓ ChatRoom created\n");

        System.out.println("STEP 2: Creating Chat Clients (Concrete Colleagues)");
        ChatClientImpl osama = new ChatClientImpl("osama", chatRoom);
        ChatClientImpl mohammad = new ChatClientImpl("mohammad", chatRoom);
        ChatClientImpl omar = new ChatClientImpl("omar", chatRoom);
        System.out.println("✓ Three clients created and registered\n");

        System.out.println("STEP 3: Sending Messages (Mediator routes them)");
        System.out.println("---");
        System.out.println("osama sends: 'Hello mohammad, how are you?'");
        osama.sendMessage("Hello mohammad, how are you?", "mohammad");
        System.out.println();

        System.out.println("mohammad sends: 'I'm great, thanks for asking!'");
        mohammad.sendMessage("I'm great, thanks for asking!", "osama");
        System.out.println();

        System.out.println("omar sends: 'Hi everyone!'");
        omar.sendMessage("Hi everyone!", "osama");
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
        System.out.println("Osama sends to 'NonExistent': 'Does this work?'");
        osama.sendMessage("Does this work?", "NonExistent");
        System.out.println();

    }

    public static void main(String[] args) {
        demonstrateMediator();
    }
}

