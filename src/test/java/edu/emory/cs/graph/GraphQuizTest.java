package edu.emory.cs.graph;

import org.junit.jupiter.api.Test;

import java.security.spec.RSAOtherPrimeInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author Jinho D. Choi */
public class GraphQuizTest {
    @Test
    public void testQuiz0() {
        GraphQuiz g = new GraphQuiz(5);
        g.setDirectedEdge(0, 1, 0);
        g.setDirectedEdge(0, 2, 0);
        g.setDirectedEdge(2, 1, 0);
        g.setDirectedEdge(2, 3, 0);
        g.setDirectedEdge(3, 4, 0);
        g.setDirectedEdge(4, 2, 0);
        System.out.println(g.numberOfCycles());
        assertEquals(1, g.numberOfCycles());
    }

    @Test
    public void testQuiz1() {
        GraphQuiz g = new GraphQuiz(5);
        g.setDirectedEdge(0, 2, 0);
        g.setDirectedEdge(1, 0, 0);
        g.setDirectedEdge(2, 1, 0);
        g.setDirectedEdge(2, 3, 0);
        g.setDirectedEdge(3, 4, 0);
        g.setDirectedEdge(4, 2, 0);
        System.out.println(g.numberOfCycles());
        assertEquals(2, g.numberOfCycles());
    }

    @Test
    public void testQuiz2() {
        GraphQuiz g = new GraphQuiz(6);
        g.setDirectedEdge(0, 2, 0);
        g.setDirectedEdge(1, 0, 0);
        g.setDirectedEdge(2, 1, 0);
        g.setDirectedEdge(3, 4, 0);
        g.setDirectedEdge(4, 5, 0);
        g.setDirectedEdge(5, 3, 0);
        int count = g.numberOfCycles();
        System.out.println(count);
        assertEquals(2, g.numberOfCycles());
    }

    @Test
    public void testQuiz3() {
        GraphQuiz g = new GraphQuiz(5);
        g.setDirectedEdge(0, 1, 0);
        g.setDirectedEdge(1, 2, 0);
        g.setDirectedEdge(1, 3, 0);
        g.setDirectedEdge(2, 0, 0);
        g.setDirectedEdge(3, 4, 0);
        g.setDirectedEdge(4, 2, 0);
        System.out.println(g.numberOfCycles());
        assertEquals(2, g.numberOfCycles());
    }

    @Test
    public void testQuiz4() {
        GraphQuiz g = new GraphQuiz(6);
        g.setDirectedEdge(0, 1, 0);
        g.setDirectedEdge(1, 2, 0);
        g.setDirectedEdge(1, 3, 0);
        g.setDirectedEdge(2, 0, 0);
        g.setDirectedEdge(2, 4, 0);
        g.setDirectedEdge(3, 4, 0);
        g.setDirectedEdge(4, 1, 0);
        g.setDirectedEdge(4, 5, 0);
        g.setDirectedEdge(5, 2, 0);
        System.out.println(g.numberOfCycles());
        assertEquals(5, g.numberOfCycles());
    }

    @Test
    public void testQuiz5() {
        GraphQuiz g = new GraphQuiz(4);
        g.setUndirectedEdge(0, 1, 0);
        g.setUndirectedEdge(1, 2, 0);
        g.setUndirectedEdge(2, 3, 0);
        g.setUndirectedEdge(3, 0, 0);
        System.out.println(g.numberOfCycles());
        assertEquals(6, g.numberOfCycles());
    }

    @Test
    public void testQuiz6() {
        GraphQuiz g = new GraphQuiz(6);
        g.setDirectedEdge(0, 1, 0);
        g.setDirectedEdge(0, 4, 0);
        g.setDirectedEdge(1, 2, 0);
        g.setDirectedEdge(1, 4, 0);
        g.setDirectedEdge(2, 0, 0);
        g.setDirectedEdge(2, 5, 0);
        g.setDirectedEdge(3, 0, 0);
        g.setDirectedEdge(4, 2, 0);
        g.setDirectedEdge(4, 3, 0);
        g.setDirectedEdge(5, 4, 0);
        System.out.println(g.numberOfCycles());
        assertEquals(7, g.numberOfCycles());
    }
}