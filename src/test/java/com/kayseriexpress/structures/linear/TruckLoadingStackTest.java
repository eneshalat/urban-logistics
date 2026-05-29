package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TruckLoadingStackTest {
    private TruckLoadingStack stack;

    @BeforeEach
    public void setUp() {
        stack = new TruckLoadingStack();
    }

    @Test
    public void testIsEmptyInitially() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPushAndPop() {
        PackageEntity p1 = new PackageEntity("P1", "A");
        stack.push(p1);
        assertFalse(stack.isEmpty());
        
        PackageEntity popped = stack.pop();
        assertEquals(p1, popped);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopEmptyStack() {
        assertNull(stack.pop());
    }

    @Test
    public void testLIFOBehavior() {
        PackageEntity p1 = new PackageEntity("P1", "A");
        PackageEntity p2 = new PackageEntity("P2", "B");
        PackageEntity p3 = new PackageEntity("P3", "C");
        
        stack.push(p1);
        stack.push(p2);
        stack.push(p3);
        
        assertEquals(p3, stack.pop());
        assertEquals(p2, stack.pop());
        assertEquals(p1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testLargeNumberOfElements() {
        for (int i = 0; i < 100; i++) {
            stack.push(new PackageEntity("P" + i, "Dest"));
        }
        
        assertFalse(stack.isEmpty());
        
        for (int i = 99; i >= 0; i--) {
            PackageEntity p = stack.pop();
            assertEquals("P" + i, p.getPackageID());
        }
        
        assertTrue(stack.isEmpty());
    }
}
