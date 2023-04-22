package edu.emory.cs.graph.flow;

import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class MaxFlow {
    private Map<Edge, Double> flow_map;
    private double maxflow;

    public MaxFlow(Graph graph) {
        init(graph);
    }

    public void init(Graph graph) {
        flow_map = new HashMap<>();
        maxflow = 0;

        for (Edge edge : graph.getAllEdges())
            flow_map.put(edge, 0d);
    }

    //	============================== Getters ==============================

    public double getMaxFlow() {
        return maxflow;
    }

    public double getResidual(Edge edge) {
        return edge.getWeight() - flow_map.get(edge);
    }

    //	============================== Setters ==============================

    public void updateFlow(List<Edge> path, double flow) {
        path.forEach(e -> updateFlow(e, flow));
        maxflow += flow;
    }

    public void updateFlow(Edge edge, double flow) {
        Double prev = flow_map.getOrDefault(edge, 0d);
        flow_map.put(edge, prev + flow);
    }
}