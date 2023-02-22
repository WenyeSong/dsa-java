package edu.emory.cs.sort.comparison;

import edu.emory.cs.sort.AbstractSort;

import java.util.Comparator;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {
    public SelectionSort() {
        this(Comparator.naturalOrder());
    }

    public SelectionSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] array, final int beginIndex, final int endIndex) {
        for (int i = endIndex; i > beginIndex; i--) {
            int max = beginIndex;

            for (int j = beginIndex + 1; j < i; j++) {
                if (compareTo(array, j, max) > 0)
                    max = j;
            }

            swap(array, max, i - 1);
        }
    }
}
