package com.kayseriexpress.structures.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CityMapGraphTest {
    private CityMapGraph graph;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        graph = new CityMapGraph();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddEdgeCreatesVertices() {
        graph.addEdge("A", "B", 10);
        
        boolean foundA = false;
        boolean foundB = false;
        
        VertexNode curr = graph.head;
        while (curr != null) {
            if (curr.name.equals("A")) foundA = true;
            if (curr.name.equals("B")) foundB = true;
            curr = curr.next;
        }
        
        assertTrue(foundA);
        assertTrue(foundB);
    }

    @Test
    public void testEdgeWeights() {
        graph.addEdge("A", "B", 10);
        
        VertexNode curr = graph.head;
        while (curr != null) {
            if (curr.name.equals("A")) {
                assertEquals(10, curr.edgeHead.weight);
                assertEquals("B", curr.edgeHead.destination);
            }
            curr = curr.next;
        }
    }

    @Test
    public void testDijkstraShortestPath() {
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 5);
        graph.addEdge("A", "C", 20); // Longer direct path
        
        DijkstraEngine.calculateShortestPath(graph, "A", "C");
        String output = outContent.toString();
        
        // Output should mention distance 15
        assertTrue(output.contains("15 km"));
        assertTrue(output.contains("C B A") || output.contains("A B C"));
    }

    @Test
    public void testDijkstraUnreachableNode() {
        graph.addEdge("A", "B", 10);
        graph.addEdge("C", "D", 5); // Disconnected
        
        DijkstraEngine.calculateShortestPath(graph, "A", "D");
        String output = outContent.toString();
        
        assertTrue(output.contains("No path"));
    }

    @Test
    public void testMSTCalculation() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "A", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "B", 2);
        graph.addEdge("A", "C", 10);
        graph.addEdge("C", "A", 10);
        
        MSTEngine.calculateMST(graph);
        String output = outContent.toString();
        
        // Output should mention weight 1 and 2
        assertTrue(output.contains("Weight: 1"));
        assertTrue(output.contains("Weight: 2"));
        // Total MST weight should be 3
        assertTrue(output.contains("Total MST Weight: 3"));
    }
}
