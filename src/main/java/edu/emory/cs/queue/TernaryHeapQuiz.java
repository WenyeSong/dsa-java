package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class TernaryHeapQuiz <T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private final List<T> keys;

    public TernaryHeapQuiz() {
        this(Comparator.naturalOrder());
    }

    public TernaryHeapQuiz(Comparator<T> priority) {
        super(priority);
        keys = new ArrayList<>();
        add(null);
    }
    @Override
    public void add(T key) {
        keys.add(key);
        swim(size());
    }
    private void swim(int k) {
        int parent;
        for (; 1 < k; k = parent) {
            parent = findParent(k);
            if (compare(parent, k) < 0) {
                Collections.swap(keys, parent, k);
            }
        }
    }

    private int findParent(int k) {
        int parent;// parent
        if (k % 3 == 0) {
            parent = k / 3;
        } else if (k % 3 == 1) {
            parent = k / 3;
        } else {
            parent = (k + 1) / 3;
        }
        return parent;
    }
    @Override
    public T remove() {
        if (isEmpty()) return null;
        Collections.swap(keys, 1, size());
        T max = keys.remove(size());
        sink();
        return max;
    }

    private void sink() {
        int maxChild;
        int k = 1;
        while (k <= size()) {
            maxChild = findMaxChild(k);
            if ((compare(k, maxChild) >= 0)) {
                break;
            }
            Collections.swap(keys, k, maxChild);
            k = maxChild;
        }
    }
    private int findMaxChild(int k) {
        int child1 = 3 * k - 1;
        int child2 = 3 * k;
        int child3 = 3 * k + 1;
        int maxChild = k;
        if (child1 > size()) {
            return k;
        }
        if (child2 > size()) {
            return child1;
        }
        if (child3 > size()) {
            return compare(child1, child2) > 0 ? child1 : child2;
        }
        if (((compare(child1, child2) >= 0) && (compare(child2, child3) >= 0)) || ((compare(child1, child3) >= 0) && (compare(child3, child2) >= 0)))
            maxChild = child1;
        else if (((compare(child2, child1) >= 0) && (compare(child1, child3) >= 0)) || ((compare(child2, child3) >= 0) && (compare(child3, child1) >= 0)))
            maxChild = child2;
        else maxChild = child3;

        return maxChild;
    }

    @Override
    public int size() {
        return keys.size() - 1;
    }

    private int compare(int i1, int i2) {
        return priority.compare(keys.get(i1), keys.get(i2));
    }
}