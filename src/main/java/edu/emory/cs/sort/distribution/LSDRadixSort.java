package edu.emory.cs.sort.distribution;

public class LSDRadixSort extends RadixSort {
    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        for (int bit = 0; bit < maxBit; bit++) {
            int div = (int)Math.pow(10, bit);
            sort(array, beginIndex, endIndex, key -> (key / div) % 10); // input, 后面是output；
        }
    }
}