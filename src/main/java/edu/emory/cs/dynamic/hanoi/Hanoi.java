package edu.emory.cs.dynamic.hanoi;

import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class Hanoi {
    /**
     * @param n            the total number of rings.
     * @param source       the source tower.
     * @param intermediate the intermediate tower.
     * @param destination  the destination tower.
     * @return a list of steps to move all rings in the source tower to the destination tower.
     */
    public abstract List<String> solve(int n, char source, char intermediate, char destination);

    protected String getKey(int n, char source, char destination) {
        return n + ":" + source + "->" + destination;
    }
}
