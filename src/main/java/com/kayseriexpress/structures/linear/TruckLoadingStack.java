package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

/**
 * Truck Loading Stack (LIFO).
 * Simulates narrow cargo spaces for loading.
 */
public class TruckLoadingStack {
    private Node top;

    /**
     * Initializes an empty stack.
     */
    public TruckLoadingStack() {
        this.top = null;
    }

    /**
     * Pushes a package onto the stack in O(1) time.
     * @param pkg The package to add.
     */
    public void push(PackageEntity pkg) {
        Node newNode = new Node(pkg);
        newNode.next = top;
        top = newNode;
    }

    /**
     * Pops a package from the stack in O(1) time.
     * @return The removed package.
     */
    public PackageEntity pop() {
        if (top == null) {
            return null; // Stack is empty
        }
        
        PackageEntity pkg = top.data;
        top = top.next;
        return pkg;
    }
    
    /**
     * Checks if the stack is empty.
     * @return true if empty.
     */
    public boolean isEmpty() {
        return top == null;
    }
}
