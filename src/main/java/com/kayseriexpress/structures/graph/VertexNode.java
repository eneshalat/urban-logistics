package com.kayseriexpress.structures.graph;

public class VertexNode {
    public String name;
    public Edge edgeHead;
    public VertexNode next;

    public VertexNode(String name) {
        this.name = name;
        this.edgeHead = null;
        this.next = null;
    }

    public void addEdge(String destination, int weight) {
        Edge newEdge = new Edge(this.name, destination, weight);
        newEdge.next = edgeHead;
        edgeHead = newEdge;
    }
}
