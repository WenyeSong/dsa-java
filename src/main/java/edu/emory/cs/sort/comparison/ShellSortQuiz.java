package edu.emory.cs.sort.comparison;

import edu.emory.cs.sort.distribution.RadixSortQuiz;

import java.util.Collections;
import java.util.Comparator;

public class ShellSortQuiz<T extends Comparable<T>> extends ShellSort<T> {
    public ShellSortQuiz() {
        this(Comparator.naturalOrder());
    }

    public ShellSortQuiz(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    protected void populateSequence(int n) {
        // TODO: to be filled
        n /= 3;

        for (int t = sequence.size() + 1; ; t++) { // size=0+1
            int h = (int) ((Math.pow(2, t) - 1));
            if (h <= n)
                sequence.add(h);
            else break;
        }
    }

    @Override
    protected int getSequenceStartIndex(int n) {
        // TODO: to be filled
        int index = Collections.binarySearch(sequence, n / 3);
        if (index < 0)
            index = -(index + 1);
        if (index == sequence.size())
            index--;
        return index;
    }
/*    public static void main (String[]args)
    {
        Integer arr[] = {802, 24, 2, 66, 66};
        int n = arr.length;
        ShellSortQuiz test = new ShellSortQuiz();
        test.sort(arr, 0, arr.length);
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }*/


}
