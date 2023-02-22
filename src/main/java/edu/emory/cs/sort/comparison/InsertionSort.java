package edu.emory.cs.sort.comparison;

import edu.emory.cs.sort.AbstractSort;

import java.util.Comparator;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> {
    public InsertionSort() {
        this(Comparator.naturalOrder());
    }

    public InsertionSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(T[] array, int beginIndex, int endIndex) {
        sort(array, beginIndex, endIndex, 1);
    }

    protected void sort(T[] array, int beginIndex, int endIndex, final int h) {
        int begin_h = beginIndex + h;

        for (int i = begin_h; i < endIndex; i++)
            for (int j = i; begin_h <= j && compareTo(array, j, j - h) < 0; j -= h)
                swap(array, j, j - h);
    }
}