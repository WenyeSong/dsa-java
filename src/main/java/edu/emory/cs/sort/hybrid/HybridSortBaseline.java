package edu.emory.cs.sort.hybrid;

import edu.emory.cs.sort.AbstractSort;
import edu.emory.cs.sort.comparison.ShellSortKnuth;
import edu.emory.cs.sort.divide_conquer.IntroSort;
import edu.emory.cs.sort.divide_conquer.QuickSort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class HybridSortBaseline<T extends Comparable<T>> implements HybridSort<T> {
    private final AbstractSort<T> engine;

    public HybridSortBaseline() {
        engine = new IntroSort<T>(new ShellSortKnuth<T>());
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] sort(T[][] input) {
        int size = Arrays.stream(input).mapToInt(t -> t.length).sum();
        T[] output = (T[])Array.newInstance(input[0][0].getClass(), size);
        int beginIndex = 0;

        for (T[] t : input) {
            System.arraycopy(t, 0, output, beginIndex, t.length);
            beginIndex += t.length;
        }

        engine.sort(output);
        return output;
    }
}