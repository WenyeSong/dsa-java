package edu.emory.cs.queue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
    /**
     * @author Jinho D. Choi
     */
    public class BinaryHeap<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
        private final List<T> keys;

        public BinaryHeap() {
            this(Comparator.naturalOrder());
        }

        public BinaryHeap(Comparator<T> priority) {
            super(priority);
            keys = new ArrayList<>();
            keys.add(null);
        }

        @Override
        public int size() {
            return keys.size() - 1;
        }

        /**
         * @param i1 the index of the first key in {@link #keys}.
         * @param i2 the index of the second key in {@link #keys}.
         * @return a negative integer, zero, or a positive integer as the first key is
         * less than, equal to, or greater than the second key.
         */
        private int compare(int i1, int i2) {
            return priority.compare(keys.get(i1), keys.get(i2));
        }

        @Override
        public void add(T key) {
            keys.add(key);
            swim(size());
        }

        private void swim(int k) {
            for (; 1 < k && compare(k / 2, k) < 0; k /= 2)
                Collections.swap(keys, k / 2, k);
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
            for (int k = 1, i = 2; i <= size(); k = i, i *= 2) {
                if (i < size() && compare(i, i + 1) < 0) i++;
                if (compare(k, i) >= 0) break;
                Collections.swap(keys, k, i);
            }
        }
    }

