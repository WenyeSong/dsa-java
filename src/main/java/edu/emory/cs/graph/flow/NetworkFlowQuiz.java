package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;
import java.util.*;

/** @author Jinho D. Choi */
public class NetworkFlowQuiz {
    /**
     * Using depth-first traverse.
     * @param graph  a directed graph.
     * @param source the ource vertex.
     * @param target the target vertex.
     * @return a set of all augmenting paths between the specific source and target vertices in the graph.
     */
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        MaxFlow mf = new MaxFlow(graph);
        Set<Subgraph> paths = new HashSet<>();
        getAugmentingPath(graph,mf, new Subgraph(), source, target,paths);
        return paths;
    }
    private void getAugmentingPath(Graph graph, MaxFlow mf, Subgraph sub, int source, int target, Set<Subgraph> paths) {
        if (source == target) paths.add(sub);
        Subgraph tmp;

        for (Edge edge : graph.getIncomingEdges(target)) {
            if (sub.contains(edge.getSource())) continue;
            if (mf.getResidual(edge) <= 0) continue;
            tmp = new Subgraph(sub);
            tmp.addEdge(edge);
            getAugmentingPath(graph, mf, tmp, source, edge.getSource(), paths);
        }

    }

    public static void main(String[] args) {
        NetworkFlowQuiz networkFlowQuiz = new NetworkFlowQuiz();
        Graph graph;
        graph = getGraph0();
        Set<Subgraph> paths = networkFlowQuiz.getAugmentingPaths(graph,0,5);
        for (Subgraph path: paths){
            System.out.println(path.getEdges());
        }
    }

    public static Graph getGraph0() {
        Graph graph = new Graph(6);
        int s = 0, t = 5;

        graph.setDirectedEdge(s, 1, 4);
        graph.setDirectedEdge(s, 2, 2);
        graph.setDirectedEdge(1, 3, 3);
        graph.setDirectedEdge(2, 3, 2);
        graph.setDirectedEdge(2, 4, 3);
        graph.setDirectedEdge(3, 2, 1);
        graph.setDirectedEdge(3, t, 2);
        graph.setDirectedEdge(4, t, 4);

        return graph;
    }
}