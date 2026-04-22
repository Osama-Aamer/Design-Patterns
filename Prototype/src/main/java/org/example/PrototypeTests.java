package org.example;


public class PrototypeTests {
    
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        System.out.println("=== Running Prototype Pattern Tests ===\n");
        
        testBookCreation();
        testBookCloning();
        testRecommendationCreation();
        testRecommendationAddBook();
        testRecommendationCloning();
        testDeepCloningIndependence();
        testRegistryStorage();
        testRegistryCloning();
        
        printResults();
    }
    
    private static void testBookCreation() {
        test("Book Creation", () -> {
            Book book = new Book("Dune", "Frank Herbert", "Science Fiction", 1965);
            assert book.getTitle().equals("Dune") : "Title mismatch";
            assert book.getAuthor().equals("Frank Herbert") : "Author mismatch";
            assert book.getGenre().equals("Science Fiction") : "Genre mismatch";
            assert book.getPublicationYear() == 1965 : "Year mismatch";
        });
    }
    
    private static void testBookCloning() {
        test("Book Cloning", () -> {
            Book original = new Book("1984", "George Orwell", "Dystopian", 1949);
            Book cloned = original.clone();
            assert cloned.getTitle().equals(original.getTitle()) : "Cloned title differs";
            assert cloned != original : "Clone is same object as original";
            assert cloned.equals(original) : "Cloned book should be equal to original";
        });
    }
    
    private static void testRecommendationCreation() {
        test("Recommendation Creation", () -> {
            Recommendation rec = new Recommendation("Fantasy Lovers");
            assert rec.getTargetAudience().equals("Fantasy Lovers") : "Target audience mismatch";
            assert rec.getBookCount() == 0 : "New recommendation should have no books";
        });
    }
    
    private static void testRecommendationAddBook() {
        test("Add Book to Recommendation", () -> {
            Recommendation rec = new Recommendation("Science Fiction");
            Book book = new Book("The Martian", "Andy Weir", "Science Fiction", 2011);
            rec.addBook(book);
            assert rec.getBookCount() == 1 : "Book count should be 1";
            
            rec.addBook(new Book("Dune", "Frank Herbert", "Science Fiction", 1965));
            assert rec.getBookCount() == 2 : "Book count should be 2";
        });
    }
    
    private static void testRecommendationCloning() {
        test("Recommendation Deep Cloning", () -> {
            Recommendation original = new Recommendation("Young Adults");
            original.addBook(new Book("The Hunger Games", "Suzanne Collins", "Dystopian", 2008));
            
            Recommendation cloned = original.clone();
            assert cloned.getTargetAudience().equals(original.getTargetAudience()) : "Audience mismatch";
            assert cloned.getBookCount() == original.getBookCount() : "Book count mismatch";
            assert cloned != original : "Clone should be different object";
        });
    }
    
    private static void testDeepCloningIndependence() {
        test("Deep Cloning Independence", () -> {
            Recommendation original = new Recommendation("Fantasy");
            original.addBook(new Book("LOTR", "Tolkien", "Fantasy", 1954));
            original.addBook(new Book("Harry Potter", "Rowling", "Fantasy", 1997));
            
            Recommendation cloned = original.clone();
            cloned.removeBookByTitle("LOTR");
            cloned.addBook(new Book("The Name of the Wind", "Rothfuss", "Fantasy", 2007));
            
            assert original.getBookCount() == 2 : "Original should still have 2 books";
            assert cloned.getBookCount() == 2 : "Clone should have 2 books (removed 1, added 1)";
            
            // Verify the titles are different
            assert !original.getBooks().get(0).getTitle().equals(cloned.getBooks().get(0).getTitle()) 
                : "Books should be different due to removal in clone";
        });
    }
    
    private static void testRegistryStorage() {
        test("Registry Storage", () -> {
            RecommendationRegistry registry = new RecommendationRegistry();
            Recommendation rec = new Recommendation("Mystery Lovers");
            
            registry.registerRecommendation("Mystery Books", rec);
            assert registry.hasRecommendation("Mystery Books") : "Recommendation should be registered";
            
            Recommendation retrieved = registry.getRecommendation("Mystery Books");
            assert retrieved.getTargetAudience().equals("Mystery Lovers") : "Retrieved recommendation mismatch";
        });
    }
    
    private static void testRegistryCloning() {
        test("Registry Cloning", () -> {
            RecommendationRegistry registry = new RecommendationRegistry();
            Recommendation original = new Recommendation("Classic Literature");
            original.addBook(new Book("Pride and Prejudice", "Jane Austen", "Romance", 1813));
            
            registry.registerRecommendation("Classics", original);
            
            Recommendation cloned = registry.cloneRecommendation("Classics");
            cloned.setTargetAudience("Historical Fiction");
            cloned.addBook(new Book("Jane Eyre", "Charlotte Bronte", "Romance", 1847));
            
            assert original.getBookCount() == 1 : "Original should have 1 book";
            assert cloned.getBookCount() == 2 : "Clone should have 2 books";
            assert original.getTargetAudience().equals("Classic Literature") : "Original audience unchanged";
            assert cloned.getTargetAudience().equals("Historical Fiction") : "Clone audience changed";
        });
    }
    
    private static void test(String testName, Runnable testCode) {
        try {
            testCode.run();
            System.out.println("✓ PASSED: " + testName);
            passedTests++;
        } catch (AssertionError e) {
            System.out.println("✗ FAILED: " + testName);
            System.out.println("  Error: " + e.getMessage());
            failedTests++;
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + testName);
            System.out.println("  Exception: " + e.getMessage());
            failedTests++;
        }
    }
    
    private static void printResults() {
        System.out.println("\n=== Test Results ===");
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
        System.out.println("Total: " + (passedTests + failedTests));
        
        if (failedTests == 0) {
            System.out.println("\n✓ All tests passed!");
        } else {
            System.out.println("\n✗ Some tests failed.");
        }
    }
}
