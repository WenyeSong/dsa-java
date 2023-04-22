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
        // TODO: to be updated
        MaxFlow mf = new MaxFlow(graph);
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.size()];
        Set<Subgraph> paths = new HashSet<>();
        Edge[] parent = new Edge[graph.size()];

        visited[source] = true;
        stack.push(source);
        List<Deque<Edge>> outgoingEdges = graph.getOutgoingEdges();

        while(! stack.empty()) {
            int v = stack.pop();

            if (v == target) { //add the path into paths
                Subgraph path = new Subgraph();
                Stack<Edge> tmp = new Stack<>();

                while(v != source){
                    Edge e = parent[v];
                    tmp.push(e);
                    v = e.getSource();
                }
                while(!tmp.empty()){
                    path.addEdge(tmp.pop());
                }

                paths.add(path);

            } else { // add neighbour of path into stack
                for(Edge edge : outgoingEdges.get(v)){

                    if(mf.getResidual(edge) <= 0) continue;// condition1

                    int nextNode = edge.getTarget();
                    if(visited[nextNode]) continue;//condition2
                    if (nextNode != target){
                        visited[nextNode] = true;
                    }
                    stack.push(nextNode);
                    parent[nextNode] = edge;
                }
            }
        }
        return paths;
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