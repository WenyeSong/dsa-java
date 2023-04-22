package edu.emory.cs.graph.flow;


import edu.emory.cs.graph.Graph;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public interface NetworkFlow {
    /**
     * @param graph  a graph.
     * @param source the source vertex.
     * @param target the target vertex.
     * @return the maximum flow from the source to the target vertices.
     */
    MaxFlow getMaximumFlow(Graph graph, int source, int target);
}