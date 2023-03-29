package edu.emory.cs.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jinho D. Choi
 */
public class GraphQuiz extends Graph {

    public GraphQuiz(int size) {
        super(size);
    }

    public GraphQuiz(Graph g) {
        super(g);
    }

    /**
     * @return the total number of cycles in this graph.
     */
    public int numberOfCycles() {
        List<Integer> trace;
        Set<Integer> searched = new HashSet<>();
        Set<List<Integer>> allCircles = new HashSet<>();
        int cycle = 0;
        // TODO: to be updated
        int size = this.size();
        for (int i = 0; i < size; i++) {
            if (searched.contains(i)) {
                continue;
            }
            trace = new ArrayList<>();
            findCycle(i, searched, trace, allCircles);
        }
        return allCircles.size();
    }
    private void findCycle(int v, Set<Integer> searched,List<Integer> trace,Set<List<Integer>> allCircles) {// find cycle from v
        int j = trace.indexOf(v);
        if (j != -1) {
            List<Integer> circle = new ArrayList<>(); // create a circle list extracting all vertices from trace list
            while (j < trace.size()) {
                circle.add(trace.get(j));
                j++;
            }
            Collections.sort(circle); // sort vertices in circle, quick sort
            allCircles.add(circle);
            return;
        }
        trace.add(v); // trace: all visited vertices

        List<Deque<Edge>> outgoingEdges = this.getOutgoingEdges();// all outgoing edges
        Deque<Edge> edges = outgoingEdges.get(v);//  v as source, edges of v
        List<Edge> edgeList = edges.stream().toList();
        // 根据边将target加入已遍历的list，并进入循环寻找环的下一条边
        for (Edge edge : edgeList) {
            searched.add(edge.getTarget());
            findCycle(edge.getTarget(), searched, trace, allCircles);
        }
        trace.remove(trace.size() - 1);
    }

}