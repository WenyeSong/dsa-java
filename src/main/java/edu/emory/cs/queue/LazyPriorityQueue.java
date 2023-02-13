package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LazyPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private final List<T> keys;

    /** Initializes this as a maximum PQ. */
    public LazyPriorityQueue() {
        this(Comparator.naturalOrder());
    }

    /** @see AbstractPriorityQueue#AbstractPriorityQueue(Comparator). */
    public LazyPriorityQueue(Comparator<T> priority) {
        super(priority);
        keys = new ArrayList<>();
    }

    @Override
    public int size() {
        return keys.size();
    }

    /**
     * Appends a key to {@link #keys}.
     * @param key the key to be added.
     */
    @Override
    public void add(T key) {
        keys.add(key);
    }

    /**
     * Finds the key with the highest/lowest priority, and removes it from {@link #keys}.
     * @return the key with the highest/lowest priority if exists; otherwise, null.
     */
    @Override
    public T remove() {
        if (isEmpty()) return null;
        T max = Collections.max(keys, priority);
        keys.remove(max);
        return max;
    }
}
