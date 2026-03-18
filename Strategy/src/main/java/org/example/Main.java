package org.example;

import java.util.Random;

/**
 * comparing the performance of
 * three sorting algorithms (Merge Sort, Quick Sort, Heap Sort) on two
 * data sets: a small array (30 elements) and a large array (100 000 elements).
 */
public class Main {

    private static final int SMALL_SIZE = 30;
    private static final int LARGE_SIZE = 100_000;
    private static final int VALUE_BOUND = 1_000_000;

    public static void main(String[] args) {
        Random random = new Random(42); // fixed seed for reproducibility

        int[] smallData = generateArray(SMALL_SIZE, VALUE_BOUND, random);
        int[] largeData = generateArray(LARGE_SIZE, VALUE_BOUND, random);

        SortingStrategy[] strategies = {
            new MergeSort(),
            new QuickSort(),
            new HeapSort()
        };

        SortingContext context = new SortingContext(strategies[0]);

        System.out.println("=== Sorting Algorithm Performance Comparison ===\n");

        // - Small data set ---
        System.out.println("--- Small array (" + SMALL_SIZE + " elements) ---");
        printArray("Unsorted", smallData);

        for (SortingStrategy strategy : strategies) {
            context.setStrategy(strategy);
            long elapsed = context.sortAndMeasure(smallData);
            System.out.printf("%-12s  time: %,d ns%n", strategy.getName(), elapsed);
        }

        System.out.println();

        // -- Large data set ---
        System.out.println("--- Large array (" + LARGE_SIZE + " elements) ---");

        for (SortingStrategy strategy : strategies) {
            context.setStrategy(strategy);
            long elapsed = context.sortAndMeasure(largeData);
            System.out.printf("%-12s  time: %,d ns  (%,.3f ms)%n",
                    strategy.getName(), elapsed, elapsed / 1_000_000.0);
        }

        System.out.println("\nDone.");
    }

    /** Generates an array of {@code size} random integers in [0, bound). */
    private static int[] generateArray(int size, int bound, Random random) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(bound);
        }
        return array;
    }

    /** Prints up to 30 elements of an array on one line. */
    private static void printArray(String label, int[] array) {
        System.out.print(label + ": [");
        int limit = Math.min(array.length, 30);
        for (int i = 0; i < limit; i++) {
            System.out.print(array[i]);
            if (i < limit - 1) System.out.print(", ");
        }
        if (array.length > 30) System.out.print(", ...");
        System.out.println("]");
    }
}