package com.kayseriexpress.structures.graph;

public class Edge {
    public String source;
    public String destination;
    public int weight;
    public Edge next;

    public Edge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.next = null;
    }
}
