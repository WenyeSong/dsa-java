package edu.emory.cs.dynamic.lcs;

/** @author Jinho D. Choi */
public abstract class LCS {
    /**
     * @param a the first string.
     * @param b the second string.
     * @return a longest common sequence of the two specific strings.
     */
    public String solve(String a, String b) {
        return solve(a.toCharArray(), b.toCharArray(), a.length() - 1, b.length() - 1);
    }

    /**
     * @param c the first array of characters.
     * @param d the second array of characters.
     * @param i the index of the character in {@code c} to be compared.
     * @param j the index of the character in {@code d} to be compared.
     * @return a longest common sequence of the two specific strings.
     */
    protected abstract String solve(char[] c, char[] d, int i, int j);
}