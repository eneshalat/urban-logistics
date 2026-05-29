package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

public class StandardDeliveryQueue {
    private Node front;
    private Node rear;

    public StandardDeliveryQueue() {
        this.front = null;
        this.rear = null;
    }

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
    
    public boolean isEmpty() {
        return front == null;
    }
}
