package edu.emory.cs.graph;

/** @author Jinho D. Choi ({@code jinho.choi@emory.edu}) */
public class Edge implements Comparable<Edge> {
    private int source;
    private int target;
    private double weight;

    public Edge(int source, int target, double weight) {
        init(source, target, weight);
    }

    public Edge(int source, int target) {
        this(source, target, 0);
    }

    public Edge(Edge edge) {
        this(edge.getSource(), edge.getTarget(), edge.getWeight());
    }

    private void init(int source, int target, double weight) {
        setSource(source);
        setTarget(target);
        setWeight(weight);
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }

    public void setSource(int vertex) {
        source = vertex;
    }

    public void setTarget(int vertex) {
        target = vertex;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void addWeight(double weight) {
        this.weight += weight;
    }

    @Override
    public int compareTo(Edge edge) {
        double diff = weight - edge.weight;
        if (diff > 0) return 1;
        else if (diff < 0) return -1;
        else return 0;
    }

    public String toString() {
        return String.format("%d <- %d : %f", getTarget(), getSource(), getWeight());
    }
}
