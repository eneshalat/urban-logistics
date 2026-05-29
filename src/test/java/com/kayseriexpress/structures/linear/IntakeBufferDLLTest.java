package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntakeBufferDLLTest {
    private IntakeBufferDLL buffer;

    @BeforeEach
    public void setUp() {
        buffer = new IntakeBufferDLL();
    }

    @Test
    public void testInsertAtTailAndRemoveFromHead() {
        PackageEntity pkg1 = new PackageEntity("P1", "Talas");
        buffer.insertAtTail(pkg1);
        
        PackageEntity removed = buffer.removeFromHead();
        assertEquals(pkg1, removed);
        assertNull(buffer.removeFromHead());
    }

    @Test
    public void testRemoveFromEmptyBuffer() {
        assertNull(buffer.removeFromHead());
    }

    @Test
    public void testFIFOBehavior() {
        PackageEntity p1 = new PackageEntity("P1", "Talas");
        PackageEntity p2 = new PackageEntity("P2", "Belsin");
        
        buffer.insertAtTail(p1);
        buffer.insertAtTail(p2);
        
        assertEquals(p1, buffer.removeFromHead());
        assertEquals(p2, buffer.removeFromHead());
    }

    @Test
    public void testRemoveSpecificPackageMiddle() {
        PackageEntity p1 = new PackageEntity("P1", "Talas");
        PackageEntity p2 = new PackageEntity("P2", "Belsin");
        PackageEntity p3 = new PackageEntity("P3", "Ildem");
        
        buffer.insertAtTail(p1);
        buffer.insertAtTail(p2);
        buffer.insertAtTail(p3);
        
        buffer.removePackage(p2);
        
        assertEquals(p1, buffer.removeFromHead());
        assertEquals(p3, buffer.removeFromHead());
        assertNull(buffer.removeFromHead());
    }

    @Test
    public void testRemoveSpecificPackageHeadAndTail() {
        PackageEntity p1 = new PackageEntity("P1", "A");
        PackageEntity p2 = new PackageEntity("P2", "B");
        
        buffer.insertAtTail(p1);
        buffer.insertAtTail(p2);
        
        buffer.removePackage(p1); // Remove head
        assertEquals(p2, buffer.removeFromHead());
        
        buffer.insertAtTail(p1);
        buffer.insertAtTail(p2);
        buffer.removePackage(p2); // Remove tail
        assertEquals(p1, buffer.removeFromHead());
    }
}
