package edu.emory.cs.set;

/** @author Jinho D. Choi */
public class DisjointSetQuiz {
    static public void main(String[] args) {
        DisjointSet ds = new DisjointSet(5);
        ds.union(0,1);
        ds.union(2,3);
        ds.union(3,4);
        ds.union(3,1);

    }
}
