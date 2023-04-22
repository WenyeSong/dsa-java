
package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;

import java.util.*;

/** @author Jinho D. Choi */
public class NetworkFlowQuizExtra {
    /**
     * Using breadth-first traverse.
     *
     * @param graph  a directed graph.
     * @param source the ource vertex.
     * @param target the target vertex.
     * @return a set of all augmenting paths between the specific source and target vertices in the graph.
     */
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target) {
        // TODO: to be updated
        MaxFlow mf = new MaxFlow(graph);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        Set<Subgraph> paths = new HashSet<>();
        Edge[] parent = new Edge[graph.size()];

        visited[source] = true;
        queue.offer(source);
        List<Deque<Edge>> outgoingEdges = graph.getOutgoingEdges();

        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (v == target) {
                // return path
                Subgraph path = new Subgraph();

                Stack<Edge> tmp = new Stack<>();
                while (v != source) {
                    Edge edge = parent[v];
                    tmp.push(edge);
                    v = edge.getSource();
                }
                while (!tmp.empty()) {
                    path.addEdge(tmp.pop());
                }
                paths.add(path);
            } else {
                for (Edge edge : outgoingEdges.get(v)) {
                    if (mf.getResidual(edge) <= 0) continue;
                    int nextNode = edge.getTarget();
                    if (visited[nextNode]) continue;
                    if (nextNode != target) {
                        visited[nextNode] = true;
                    }
                    parent[nextNode] = edge;
                    queue.offer(edge.getTarget());
                }
            }
        }
        return paths;
    }
    public static void main(String[] args) {
        NetworkFlowQuizExtra networkFlowQuizExtra = new NetworkFlowQuizExtra();
        Graph graph;
        graph = getGraph0();
        Set<Subgraph> paths = networkFlowQuizExtra.getAugmentingPaths(graph,0,5);
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