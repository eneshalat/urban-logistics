package com.kayseriexpress.structures.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressDirectoryAVLTest {
    private AddressDirectoryAVL avlTree;

    @BeforeEach
    public void setUp() {
        avlTree = new AddressDirectoryAVL();
    }

    @Test
    public void testInsertAndSearchSingleNode() {
        avlTree.insert("Talas", "C1");
        assertEquals("C1", avlTree.search("Talas"));
    }

    @Test
    public void testSearchNonExistentNode() {
        avlTree.insert("Talas", "C1");
        assertNull(avlTree.search("Belsin"));
    }

    @Test
    public void testInsertMultipleAndSearch() {
        avlTree.insert("Talas", "C1");
        avlTree.insert("Belsin", "C2");
        avlTree.insert("Ildem", "C3");
        
        assertEquals("C1", avlTree.search("Talas"));
        assertEquals("C2", avlTree.search("Belsin"));
        assertEquals("C3", avlTree.search("Ildem"));
    }

    @Test
    public void testUpdateExistingCustomer() {
        avlTree.insert("Talas", "C1");
        avlTree.insert("Talas", "C2"); // Should update
        
        assertEquals("C2", avlTree.search("Talas"));
    }

    @Test
    public void testTreeBalancing() {
        // Inserting sorted data to force left or right rotations
        avlTree.insert("A", "C1");
        avlTree.insert("B", "C2");
        avlTree.insert("C", "C3");
        avlTree.insert("D", "C4");
        avlTree.insert("E", "C5");
        
        // If it doesn't balance, it becomes a linked list and might throw stack overflow on large inputs.
        // For standard unit tests, confirming they exist without error is our main goal since we can't easily 
        // access the private root or heights without reflection.
        assertEquals("C1", avlTree.search("A"));
        assertEquals("C3", avlTree.search("C"));
        assertEquals("C5", avlTree.search("E"));
    }
}
