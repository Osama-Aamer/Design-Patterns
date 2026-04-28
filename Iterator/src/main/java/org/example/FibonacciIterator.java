package org.example;

import java.util.Iterator;

class FibonacciIterator implements Iterator<Integer> {
    private int prev = 1;
    private int curr = 1;
    private boolean first = true;

    // State lives in the iterator so multiple iterators can run independently.
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        if (first) {
            first = false;
            return 1;
        }
        int value = curr;
        int next = prev + curr;
        prev = curr;
        curr = next;
        return value;
    }
}
