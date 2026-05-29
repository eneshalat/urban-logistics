package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StandardDeliveryQueueTest {
    private StandardDeliveryQueue queue;

    @BeforeEach
    public void setUp() {
        queue = new StandardDeliveryQueue();
    }

    @Test
    public void testIsEmptyInitially() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testEnqueueAndDequeue() {
        PackageEntity p1 = new PackageEntity("P1", "A");
        queue.enqueue(p1);
        assertFalse(queue.isEmpty());
        
        PackageEntity dequeued = queue.dequeue();
        assertEquals(p1, dequeued);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertNull(queue.dequeue());
    }

    @Test
    public void testMultipleEnqueueDequeue() {
        PackageEntity p1 = new PackageEntity("P1", "A");
        PackageEntity p2 = new PackageEntity("P2", "B");
        PackageEntity p3 = new PackageEntity("P3", "C");
        
        queue.enqueue(p1);
        queue.enqueue(p2);
        queue.enqueue(p3);
        
        assertEquals(p1, queue.dequeue());
        assertEquals(p2, queue.dequeue());
        
        queue.enqueue(new PackageEntity("P4", "D"));
        
        assertEquals(p3, queue.dequeue());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(new PackageEntity("P" + i, "Dest"));
        }
        
        assertFalse(queue.isEmpty());
        
        for (int i = 0; i < 1000; i++) {
            PackageEntity p = queue.dequeue();
            assertEquals("P" + i, p.getPackageID());
        }
        
        assertTrue(queue.isEmpty());
    }
}
