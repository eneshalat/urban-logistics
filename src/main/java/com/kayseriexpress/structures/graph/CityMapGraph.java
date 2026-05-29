package com.kayseriexpress.structures.graph;

public class CityMapGraph {
    public VertexNode head;

    public CityMapGraph() {
        this.head = null;
    }

    private VertexNode getOrCreateVertex(String name) {
        VertexNode curr = head;
        while (curr != null) {
            if (curr.name.equals(name)) {
                return curr;
            }
            curr = curr.next;
        }
        VertexNode newNode = new VertexNode(name);
        newNode.next = head;
        head = newNode;
        return newNode;
    }

    public void addEdge(String source, String destination, int weight) {
        VertexNode srcNode = getOrCreateVertex(source);
        getOrCreateVertex(destination); // Ensure destination exists
        srcNode.addEdge(destination, weight);
    }
}
