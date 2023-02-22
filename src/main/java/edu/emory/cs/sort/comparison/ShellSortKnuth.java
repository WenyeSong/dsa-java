package edu.emory.cs.sort.comparison;

import java.util.Collections;
import java.util.Comparator;

public class ShellSortKnuth<T extends Comparable<T>> extends ShellSort<T> {
    public ShellSortKnuth() {
        this(Comparator.naturalOrder());
    }

    public ShellSortKnuth(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    protected void populateSequence(int n) {
        n /= 3;

        for (int t = sequence.size() + 1; ; t++) { // size=0+1
            int h = (int) ((Math.pow(3, t) - 1) / 2);
            if (h <= n)
                sequence.add(h);
            else break;
        }
    }

    @Override
    protected int getSequenceStartIndex(int n) {
        int index = Collections.binarySearch(sequence, n / 3);
        if (index < 0) index = -(index + 1);
        if (index == sequence.size()) index--;
        return index;
    }
}