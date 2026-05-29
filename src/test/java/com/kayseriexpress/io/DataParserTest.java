package com.kayseriexpress.io;

import com.kayseriexpress.structures.graph.CityMapGraph;
import com.kayseriexpress.structures.graph.VertexNode;
import com.kayseriexpress.structures.linear.IntakeBufferDLL;
import com.kayseriexpress.structures.tree.AddressDirectoryAVL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DataParserTest {

    private File tempMapFile;
    private File tempPackageFile;
    private CityMapGraph graph;
    private IntakeBufferDLL buffer;
    private AddressDirectoryAVL directory;

    @BeforeEach
    public void setUp() throws IOException {
        graph = new CityMapGraph();
        buffer = new IntakeBufferDLL();
        directory = new AddressDirectoryAVL();

        // Create temporary map data file
        tempMapFile = File.createTempFile("testMap", ".txt");
        try (FileWriter writer = new FileWriter(tempMapFile)) {
            writer.write("A B 10\n");
            writer.write("B C 20\n");
        }

        // Create temporary package data file
        tempPackageFile = File.createTempFile("testPackage", ".txt");
        try (FileWriter writer = new FileWriter(tempPackageFile)) {
            writer.write("PKG1 Talas CUST1\n");
            writer.write("PKG2 Belsin CUST2\n");
        }
    }

    @AfterEach
    public void tearDown() {
        if (tempMapFile != null && tempMapFile.exists()) {
            tempMapFile.delete();
        }
        if (tempPackageFile != null && tempPackageFile.exists()) {
            tempPackageFile.delete();
        }
    }

    @Test
    public void testLoadMapDataPopulatesGraph() {
        DataParser.loadMapData(tempMapFile.getAbsolutePath(), graph);

        boolean foundA = false;
        boolean foundB = false;
        boolean foundC = false;

        VertexNode curr = graph.head;
        while (curr != null) {
            if (curr.name.equals("A")) foundA = true;
            if (curr.name.equals("B")) foundB = true;
            if (curr.name.equals("C")) foundC = true;
            curr = curr.next;
        }

        assertTrue(foundA, "Graph should contain vertex A");
        assertTrue(foundB, "Graph should contain vertex B");
        assertTrue(foundC, "Graph should contain vertex C");
    }

    @Test
    public void testLoadMapDataHandlesEmptyLinesAndErrors() {
        assertDoesNotThrow(() -> {
            DataParser.loadMapData("non_existent_file_12345.txt", graph);
        }, "Should catch IOException and not throw");
    }

    @Test
    public void testLoadPackageDataPopulatesBuffer() {
        DataParser.loadPackageData(tempPackageFile.getAbsolutePath(), buffer, directory);
        
        assertNotNull(buffer.removeFromHead(), "Buffer should have elements");
        assertNotNull(buffer.removeFromHead(), "Buffer should have a second element");
        assertNull(buffer.removeFromHead(), "Buffer should be empty after 2 elements");
    }

    @Test
    public void testLoadPackageDataPopulatesDirectory() {
        DataParser.loadPackageData(tempPackageFile.getAbsolutePath(), buffer, directory);

        assertEquals("CUST1", directory.search("Talas"));
        assertEquals("CUST2", directory.search("Belsin"));
    }

    @Test
    public void testLoadPackageDataHandlesMissingFile() {
        assertDoesNotThrow(() -> {
            DataParser.loadPackageData("non_existent_file_12345.txt", buffer, directory);
        }, "Should catch IOException and not throw");
    }
}
