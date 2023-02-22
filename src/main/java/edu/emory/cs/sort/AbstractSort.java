package edu.emory.cs.sort;

import java.util.Comparator;


public abstract class AbstractSort<T extends Comparable<T>> {
    private final Comparator<T> comparator;
    protected long comparisons;
    protected long assignments;

    /** @param comparator specifies the precedence of comparable keys. */
    public AbstractSort(Comparator<T> comparator) {
        this.comparator = comparator;
        resetCounts();
    }

    /** @return the total number of comparisons performed during sort. */
    public long getComparisonCount() {
        return comparisons;
    }

    /** @return the total number of assignments performed during sort. */
    public long getAssignmentCount() {
        return assignments;
    }

    public void resetCounts() {
        comparisons = assignments = 0;
    }

    /**
     * @param array an array of comparable keys.
     * @param i     the index of the first key.
     * @param j     the index of the second key.
     * @return array[i].compareTo(array[j]).
     */
    protected int compareTo(T[] array, int i, int j) {
        comparisons++;
        return comparator.compare(array[i], array[j]);
    }

    /**
     * array[index] = value.
     * @param array an array of comparable keys.
     * @param index the index of the array to assign.
     * @param value the value to be assigned.
     */
    protected void assign(T[] array, int index, T value) {
        assignments++;
        array[index] = value;
    }

    /**
     * Swaps array[i] and array[j].
     * @param array an array of comparable keys.
     * @param i     the index of the first key.
     * @param j     the index of the second key.
     */
    protected void swap(T[] array, int i, int j) {
        T t = array[i];
        assign(array, i, array[j]);
        assign(array, j, t);
    }

    /**
     * Sorts the array in ascending order.
     * @param array an array of comparable keys.
     */
    public void sort(T[] array) {
        sort(array, 0, array.length);
    }

    /**
     * Sorts the array[beginIndex:endIndex].
     * @param array      an array of comparable keys.
     * @param beginIndex the index of the first key to be sorted (inclusive).
     * @param endIndex   the index of the last key to be sorted (exclusive).
     */
    abstract public void sort(T[] array, int beginIndex, int endIndex);
}
