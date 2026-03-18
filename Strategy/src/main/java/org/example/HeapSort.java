package org.example;


public class HeapSort implements SortingStrategy {

    @Override
    public String getName() {
        return "Heap Sort";
    }

    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 1) return;
        int n = array.length;

        // Step 1: Build a max-heap.
        // Start from the last non-leaf node and heapify down to the root.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Step 2: Repeatedly extract the maximum (root) and restore the heap.
        for (int i = n - 1; i > 0; i--) {
            // Moves the current root (maximum) to the end.
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Restore the heap property on the reduced heap.
            heapify(array, i, 0);
        }
    }

    /**
     * Maintains maxheap property by comparing parent nodes with children and swapping if necessarry
     */
    private void heapify(int[] array, int size, int root) {
        int largest = root;
        int left    = 2 * root + 1;
        int right   = 2 * root + 2;

        if (left  < size && array[left]  > array[largest]) largest = left;
        if (right < size && array[right] > array[largest]) largest = right;

        if (largest != root) {
            int temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;
            heapify(array, size, largest);
        }
    }
}

