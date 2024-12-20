package edu.emory.cs.dynamic.lcs;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class LCSDynamic extends LCS {
    @Override
    protected String solve(char[] c, char[] d, int i, int j) {
        return solve(c, d, i, j, createTable(c, d));
    }

    /**
     * @param c the first string.
     * @param d the second string.
     * @return the dynamic table populated by estimating the # of LCSs in the grid of the two specific strings.
     */
    protected int[][] createTable(char[] c, char[] d) {
        final int N = c.length, M = d.length;
        int[][] table = new int[N][M];

        for (int i = 1; i < N; i++)
            for (int j = 1; j < M; j++)
                table[i][j] = (c[i] == d[j]) ? table[i - 1][j - 1] + 1 : Math.max(table[i - 1][j], table[i][j - 1]);

        return table;
    }

    protected String solve(char[] c, char[] d, int i, int j, int[][] table) {
        if (i < 0 || j < 0) return "";

        // a common sequence is found
        if (c[i] == d[j])  return solve(c, d, i - 1, j - 1, table) + c[i];
        // search on the first string has been exhausted
        if (i == 0) return solve(c, d, i, j - 1, table);
        // search on the second string has been exhausted
        if (j == 0) return solve(c, d, i - 1, j, table);

        return (table[i - 1][j] > table[i][j - 1]) ? solve(c, d, i - 1, j, table) : solve(c, d, i, j - 1, table);
    }
}
