package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

/**
 * Standard Delivery Queue (FIFO).
 * Simulates the sorting station operations.
 */
public class StandardDeliveryQueue {
    private Node front;
    private Node rear;

    /**
     * Initializes an empty delivery queue.
     */
    public StandardDeliveryQueue() {
        this.front = null;
        this.rear = null;
    }

    /**
     * Enqueues a package in O(1) time at the rear.
     * @param pkg The package to add.
     */
    public void enqueue(PackageEntity pkg) {
        Node newNode = new Node(pkg);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    /**
     * Dequeues a package in O(1) time from the front.
     * @return The removed package.
     */
    public PackageEntity dequeue() {
        if (front == null) {
            return null; // Queue is empty
        }
        
        PackageEntity pkg = front.data;
        front = front.next;
        
        if (front == null) {
            rear = null; // Queue became empty
        }
        
        return pkg;
    }
    
    /**
     * Checks if the queue is empty.
     * @return true if empty.
     */
    public boolean isEmpty() {
        return front == null;
    }
}
