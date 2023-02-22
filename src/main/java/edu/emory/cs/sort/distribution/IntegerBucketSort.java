package edu.emory.cs.sort.distribution;

public class IntegerBucketSort extends BucketSort<Integer> {
    private final int MIN;

    /**
     * @param min the minimum integer (inclusive).
     * @param max the maximum integer (exclusive).
     */
    public IntegerBucketSort(int min, int max) {
        super(max - min);
        MIN = min;
    }

    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        sort(array, beginIndex, endIndex, key -> key - MIN);
    }
}