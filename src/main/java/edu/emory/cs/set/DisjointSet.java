package edu.emory.cs.set;

import java.util.Arrays;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class DisjointSet {
    public final int[] subsets;

    public DisjointSet(int size) {
        subsets = new int[size];
        Arrays.fill(subsets, -1);
    }

    public DisjointSet(DisjointSet set) {
        subsets = Arrays.copyOf(set.subsets, set.subsets.length);
    }

    /**
     * @param key the specific key to search.
     * @return the ID of the subset where the specific key belongs to.
     */
    public int find(int key) {
        return (subsets[key] < 0) ? key : (subsets[key] = find(subsets[key]));
    }

    /**
     * @param key1 the first key to be compared.
     * @param key2 the second key to be compared.
     * @return true if the two specific keys are in the same set; otherwise, false.
     */
    public boolean inSameSet(int key1, int key2) {
        return find(key1) == find(key2);
    }

    public int union(int key1, int key2) {
        int r1 = find(key1);
        int r2 = find(key2);
        if (r1 == r2) return r1;

        if (subsets[r1] < subsets[r2]) {
            subsets[r1] += subsets[r2];
            subsets[r2] = r1;
            return r1;
        }
        else {
            subsets[r2] += subsets[r1];
            subsets[r1] = r2;
            return r2;
        }
    }

    public String toString() {
        return Arrays.toString(subsets);
    }
}
