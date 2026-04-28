package org.example;

import java.util.Iterator;

class FibonacciSequence implements Sequence {
    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciIterator();
    }
}
