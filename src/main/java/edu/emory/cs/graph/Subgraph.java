package edu.emory.cs.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class Subgraph {
    private final List<Edge> edges;
    private final Set<Integer> vertices;

    public Subgraph() {
        edges = new ArrayList<>();
        vertices = new HashSet<>();
    }

    public Subgraph(Subgraph graph) {
        edges = new ArrayList<>(graph.getEdges());
        vertices = new HashSet<>(graph.getVertices());
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        vertices.add(edge.getSource());
        vertices.add(edge.getTarget());
    }

    public boolean contains(int vertex) {
        return vertices.contains(vertex);
    }
}
