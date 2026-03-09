package org.example;

/**
 * Strategy interface for sorting algorithms.
 * Each concrete sorting algorithm implements this interface.
 */
public interface SortingStrategy {


     //Sorts the given array of integers in ascending order.

    void sort(int[] array);

     //Returns the name of the sorting algorithm.
    String getName();
}

