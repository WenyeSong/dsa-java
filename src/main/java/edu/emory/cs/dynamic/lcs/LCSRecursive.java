package edu.emory.cs.dynamic.lcs;

/** @author Jinho D. Choi */
public class LCSRecursive extends LCS {
    @Override
    protected String solve(char[] c, char[] d, int i, int j) {
        if (i < 0 || j < 0) return "";
        if (c[i] == d[j]) return solve(c, d, i - 1, j - 1) + c[i];

        // get the LCS between c[:i-1] and d[:j]
        String c1 = solve(c, d, i - 1, j);
        // get the LCS between c[:i] and d[:j-1]
        String d1 = solve(c, d, i, j - 1);
        return (c1.length() > d1.length()) ? c1 : d1;
    }
}