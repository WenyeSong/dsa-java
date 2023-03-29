package edu.emory.cs.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class GraphTest {
    @Test
    public void testContainsCycle() {
        Graph graph = new Graph(5);

        graph.setDirectedEdge(0, 1, 1);
        graph.setDirectedEdge(0, 2, 1);
        graph.setDirectedEdge(2, 1, 1);
        graph.setDirectedEdge(2, 3, 1);
        graph.setDirectedEdge(3, 4, 1);
        graph.setDirectedEdge(4, 2, 1);

        System.out.println(graph.toString());
        assertTrue(graph.containsCycle());
    }

    @Test
    public void testTopologicalSort() {
        Graph graph = new Graph(8);

        graph.setDirectedEdge(0, 3, 1);
        graph.setDirectedEdge(0, 4, 1);
        graph.setDirectedEdge(1, 3, 1);
        graph.setDirectedEdge(2, 4, 1);
        graph.setDirectedEdge(3, 5, 1);
        graph.setDirectedEdge(3, 6, 1);
        graph.setDirectedEdge(3, 7, 1);
        graph.setDirectedEdge(4, 6, 1);
        graph.setDirectedEdge(2, 7, 1);

        assertEquals("[0, 1, 2, 3, 4, 5, 7, 6]", new Graph(graph).topological_sort(false).toString());
        assertEquals("[0, 1, 3, 5, 2, 4, 6, 7]", new Graph(graph).topological_sort(true).toString());
    }
}