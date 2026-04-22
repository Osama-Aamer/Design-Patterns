package org.example;

import java.util.ArrayList;
import java.util.List;


public class Recommendation implements Cloneable {
    private String targetAudience;
    private List<Book> books;

    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
        this.books = new ArrayList<>();
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void removeBookByTitle(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
    }

    public int getBookCount() {
        return books.size();
    }

    /**
     * Deep clone method - creates a new Recommendation with cloned books.
     * This ensures that the cloned recommendation has its own list of books,
     * not just references to the original books.
     */
    @Override
    public Recommendation clone() {
        try {
            Recommendation clonedRecommendation = (Recommendation) super.clone();
            // Deep copy: create new list and clone each book
            clonedRecommendation.books = new ArrayList<>();
            for (Book book : this.books) {
                clonedRecommendation.addBook(book.clone());
            }
            return clonedRecommendation;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed for Recommendation", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Recommendation for ").append(targetAudience).append(" ===\n");
        if (books.isEmpty()) {
            sb.append("  No books in this recommendation.\n");
        } else {
            for (int i = 0; i < books.size(); i++) {
                sb.append(String.format("  %d. %s\n", i + 1, books.get(i)));
            }
        }
        return sb.toString();
    }
}
