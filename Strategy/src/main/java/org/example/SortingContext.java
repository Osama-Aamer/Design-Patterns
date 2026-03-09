package org.example;

/**
 * Context class for the Strategy pattern.
 * Holds a reference to a SortingStrategy and delegates the sorting task to it.
 * The strategy can be swapped at any time via setStrategy().
 */
public class SortingContext {

    private SortingStrategy strategy;

    public SortingContext(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public String getStrategyName() {
        return strategy.getName();
    }

    /**
     * Sorts a copy of the given array using the current strategy and returns
     * the elapsed time in nanoseconds.
     */
    public long sortAndMeasure(int[] original) {
        // Work on a fresh copy so each strategy gets the same unsorted input.
        int[] copy = original.clone();
        long start = System.nanoTime();
        strategy.sort(copy);
        return System.nanoTime() - start;
    }
}

