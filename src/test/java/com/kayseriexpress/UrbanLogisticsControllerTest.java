package com.kayseriexpress;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrbanLogisticsControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture the console output printed by the main method
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMainControllerExecutionDoesNotThrow() {
        // Since data/mapData.txt and data/packageData.txt exist in the project root,
        // we can run the main method directly.
        assertDoesNotThrow(() -> {
            UrbanLogisticsController.main(new String[]{});
        }, "Main controller should run from start to finish without throwing any exceptions.");
    }

    @Test
    public void testMainControllerOutputFlow() {
        UrbanLogisticsController.main(new String[]{});
        String output = outContent.toString();

        // 1. Verify Branding
        assertTrue(output.contains("Kayseri Express Logistics - Meydan Hub"), "Should print branding banner");

        // 2. Verify Data Parsing
        assertTrue(output.contains("Map data loaded successfully."), "Should load map data");
        assertTrue(output.contains("Package data loaded successfully."), "Should load package data");

        // 3. Verify Package Intake Processing
        assertTrue(output.contains("Processing Incoming Packages"), "Should process intake buffer");
        
        // 4. Verify Truck Loading Output
        assertTrue(output.contains("Loading Delivery Van (LIFO)"), "Should load delivery stack");

        // 5. Verify City Routing 
        assertTrue(output.contains("Routing Optimization"), "Should calculate Dijkstra shortest path");
        assertTrue(output.contains("Minimum Spanning Tree (Prim's Algorithm):"), "Should calculate MST");

        // 6. Verify Daily Log
        assertTrue(output.contains("Master Registry Audit Log"), "Should display the master registry SLL");
    }
}
