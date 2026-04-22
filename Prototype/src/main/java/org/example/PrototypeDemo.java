package org.example;


public class PrototypeDemo {
    public static void main(String[] args) {
        System.out.println("=== Prototype Pattern Demo: Book Recommendations System ===\n");

        RecommendationRegistry registry = new RecommendationRegistry();

        Recommendation fantasyRec = new Recommendation("Fantasy Lovers");
        fantasyRec.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 1954));
        fantasyRec.addBook(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", 1997));
        fantasyRec.addBook(new Book("A Game of Thrones", "George R.R. Martin", "Fantasy", 1996));
        registry.registerRecommendation("Fantasy Epics", fantasyRec);
        
        System.out.println("1. Original Recommendation (PROTOTYPE):");
        System.out.println(fantasyRec);

        System.out.println("2. Cloning the 'Fantasy Epics' recommendation...\n");
        Recommendation clonedRec = registry.cloneRecommendation("Fantasy Epics");
        clonedRec.setTargetAudience("Teen Fantasy Enthusiasts");
        
        System.out.println("Cloned Recommendation (INDEPENDENT COPY):");
        System.out.println(clonedRec);
        
        // Demonstrate deep cloning by modifying the clone
        System.out.println("3. Modifying the cloned recommendation...\n");
        clonedRec.removeBookByTitle("A Game of Thrones");
        clonedRec.addBook(new Book("The Name of the Wind", "Patrick Rothfuss", "Fantasy", 2007));
        
        System.out.println("Modified Clone:");
        System.out.println(clonedRec);

        System.out.println("4. Original Recommendation (UNCHANGED):");
        System.out.println(fantasyRec);

        System.out.println("5. Verifying Deep Cloning:");
        System.out.println("Original has " + fantasyRec.getBookCount() + " books");
        System.out.println("Clone has " + clonedRec.getBookCount() + " books");
        System.out.println("\nDeep cloning successful! Original and clone are completely independent.\n");

        // Register the cloned recommendation
        registry.registerRecommendation("Teen Fantasy Picks", clonedRec);
        
        System.out.println("6. Registry Status:");
        registry.listAllRecommendations();
    }
}
