package org.example;

import java.util.Scanner;

public class Main {
    private static final RecommendationRegistry registry = new RecommendationRegistry();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeDefaultRecommendations();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> viewRecommendations();
                case 2 -> cloneAndModifyRecommendation();
                case 3 -> createNewRecommendation();
                case 4 -> addBookToRecommendation();
                case 5 -> removeBookFromRecommendation();
                case 6 -> viewDetailedRecommendation();
                case 7 -> demonstrateDeepCloning();
                case 0 -> {
                    running = false;
                    System.out.println("\nThank you for using the Book Recommendations System!");
                }
                default -> System.out.println("❌ Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  Book Recommendations System (Prototype) ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("1. View all recommendations");
        System.out.println("2. Clone and modify a recommendation");
        System.out.println("3. Create new recommendation from scratch");
        System.out.println("4. Add a book to an existing recommendation");
        System.out.println("5. Remove a book from a recommendation");
        System.out.println("6. View detailed recommendation");
        System.out.println("7. Demonstrate deep cloning");
        System.out.println("0. Exit");
        System.out.print("\nChoose an option: ");
    }

    private static void initializeDefaultRecommendations() {
        Recommendation youngAdultRec = new Recommendation("Young Adults");
        youngAdultRec.addBook(new Book("The Hunger Games", "Suzanne Collins", "Dystopian", 2008));
        youngAdultRec.addBook(new Book("Percy Jackson and the Olympians", "Rick Riordan", "Fantasy", 2005));
        youngAdultRec.addBook(new Book("The Fault in Our Stars", "John Green", "Romance", 2012));
        youngAdultRec.addBook(new Book("Divergent", "Veronica Roth", "Dystopian", 2011));
        registry.registerRecommendation("Young Adult Picks", youngAdultRec);

        Recommendation sciFiRec = new Recommendation("Science Fiction Enthusiasts");
        sciFiRec.addBook(new Book("Dune", "Frank Herbert", "Science Fiction", 1965));
        sciFiRec.addBook(new Book("1984", "George Orwell", "Dystopian", 1949));
        sciFiRec.addBook(new Book("The Martian", "Andy Weir", "Science Fiction", 2011));
        sciFiRec.addBook(new Book("Foundation", "Isaac Asimov", "Science Fiction", 1951));
        registry.registerRecommendation("Sci-Fi Classics", sciFiRec);

        Recommendation fantasyRec = new Recommendation("Fantasy Lovers");
        fantasyRec.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 1954));
        fantasyRec.addBook(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", 1997));
        fantasyRec.addBook(new Book("A Game of Thrones", "George R.R. Martin", "Fantasy", 1996));
        registry.registerRecommendation("Fantasy Epics", fantasyRec);

        System.out.println("✓ System initialized with 3 default recommendations.\n");
    }

    private static void viewRecommendations() {
        System.out.println();
        registry.listAllRecommendations();
    }

    private static void cloneAndModifyRecommendation() {
        System.out.println();
        registry.listAllRecommendations();

        System.out.print("Enter the name of the recommendation to clone: ");
        String name = scanner.nextLine().trim();

        if (!registry.hasRecommendation(name)) {
            System.out.println("❌ Recommendation not found!\n");
            return;
        }

        // Clone using Prototype pattern
        Recommendation cloned = registry.cloneRecommendation(name);

        System.out.print("Enter a new name for the cloned recommendation: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("❌ Name cannot be empty.\n");
            return;
        }

        if (registry.hasRecommendation(newName)) {
            System.out.print("⚠ A recommendation with that name already exists. Overwrite? (y/n): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println("Cancelled.\n");
                return;
            }
        }

        System.out.print("Enter target audience for the cloned recommendation (or press Enter to keep original): ");
        String audience = scanner.nextLine().trim();
        if (!audience.isEmpty()) {
            cloned.setTargetAudience(audience);
        }

        registry.registerRecommendation(newName, cloned);
        System.out.println("✓ Recommendation cloned and saved as '" + newName + "'!\n");
        System.out.println(cloned);
    }

    private static void createNewRecommendation() {
        System.out.println();
        System.out.print("Enter a name for the new recommendation: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("❌ Name cannot be empty.\n");
            return;
        }

        if (registry.hasRecommendation(name)) {
            System.out.println("❌ A recommendation with that name already exists.\n");
            return;
        }

        System.out.print("Enter target audience: ");
        String audience = scanner.nextLine().trim();

        if (audience.isEmpty()) {
            audience = "General Audience";
        }

        Recommendation newRec = new Recommendation(audience);
        registry.registerRecommendation(name, newRec);
        System.out.println("✓ New recommendation '" + name + "' created!\n");
    }

    private static void addBookToRecommendation() {
        System.out.println();
        registry.listAllRecommendations();

        System.out.print("Enter the name of the recommendation: ");
        String name = scanner.nextLine().trim();

        Recommendation rec = registry.getRecommendation(name);
        if (rec == null) {
            System.out.println("❌ Recommendation not found!\n");
            return;
        }

        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.\n");
            return;
        }

        System.out.print("Enter book author: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            System.out.println("❌ Author cannot be empty.\n");
            return;
        }

        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine().trim();
        if (genre.isEmpty()) {
            genre = "Unknown";
        }

        System.out.print("Enter publication year: ");
        int year = readInt();

        rec.addBook(new Book(title, author, genre, year));
        System.out.println("✓ Book added to recommendation!\n");
    }

    private static void removeBookFromRecommendation() {
        System.out.println();
        registry.listAllRecommendations();

        System.out.print("Enter the name of the recommendation: ");
        String name = scanner.nextLine().trim();

        Recommendation rec = registry.getRecommendation(name);
        if (rec == null) {
            System.out.println("❌ Recommendation not found!\n");
            return;
        }

        if (rec.getBookCount() == 0) {
            System.out.println("❌ This recommendation has no books.\n");
            return;
        }

        System.out.println(rec);
        System.out.print("Enter the title of the book to remove: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.\n");
            return;
        }

        int beforeCount = rec.getBookCount();
        rec.removeBookByTitle(title);
        int afterCount = rec.getBookCount();

        if (beforeCount == afterCount) {
            System.out.println("⚠ Book not found (no books removed).\n");
        } else {
            System.out.println("✓ Book removed from recommendation!\n");
        }
    }

    private static void viewDetailedRecommendation() {
        System.out.println();
        registry.listAllRecommendations();

        System.out.print("Enter the name of the recommendation to view: ");
        String name = scanner.nextLine().trim();

        Recommendation rec = registry.getRecommendation(name);
        if (rec == null) {
            System.out.println("❌ Recommendation not found!\n");
            return;
        }

        System.out.println("\n" + rec);
    }

    private static void demonstrateDeepCloning() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("DEMONSTRATING DEEP CLONING (Prototype Pattern)");
        System.out.println("═══════════════════════════════════════════");

        // Create original
        Recommendation original = new Recommendation("Tech Lovers");
        original.addBook(new Book("Clean Code", "Robert C. Martin", "Programming", 2008));
        original.addBook(new Book("Design Patterns", "Gang of Four", "Programming", 1994));

        System.out.println("\n1. ORIGINAL RECOMMENDATION:");
        System.out.println(original);

        // Clone it
        Recommendation cloned = original.clone();
        cloned.setTargetAudience("Junior Developers");

        System.out.println("2. CLONED RECOMMENDATION (independent copy):");
        System.out.println(cloned);

        // Modify clone
        cloned.removeBookByTitle("Design Patterns");
        cloned.addBook(new Book("Refactoring", "Martin Fowler", "Programming", 1999));

        System.out.println("3. AFTER MODIFYING CLONE:");
        System.out.println("   Clone now has: " + cloned.getBookCount() + " books");
        System.out.println("   Original still has: " + original.getBookCount() + " books");

        System.out.println("\n4. PROOF OF INDEPENDENCE:");
        System.out.println("   Original books: " + original.getBooks());
        System.out.println("   Cloned books: " + cloned.getBooks());

        System.out.println("\n✓ DEEP CLONING VERIFIED - Original and clone are completely independent!\n");
    }

    private static int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("❌ Please enter a number: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid number. Try again: ");
            }
        }
    }
}
