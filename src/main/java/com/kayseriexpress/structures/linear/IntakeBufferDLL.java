package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

/**
 * Doubly Linked List buffer for rapid incoming packages.
 * Optimized for O(1) tail additions.
 */
public class IntakeBufferDLL {
    private DLLNode head;
    private DLLNode tail;

    /**
     * Initializes an empty intake buffer.
     */
    public IntakeBufferDLL() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Inserts package at the tail in O(1) time.
     * Direct pointer updates ensure constant time complexity.
     * @param pkg The package to add.
     */
    public void insertAtTail(PackageEntity pkg) {
        DLLNode newNode = new DLLNode(pkg);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /**
     * Removes and returns the head package in O(1) time.
     * Constant time since it directly accesses head.
     * @return The removed package.
     */
    public PackageEntity removeFromHead() {
        if (head == null) {
            return null; // Buffer is empty
        }
        
        PackageEntity pkg = head.data;
        head = head.next;
        
        if (head != null) {
            head.prev = null;
        } else {
            tail = null; // Buffer became empty
        }
        
        return pkg;
    }
    
    /**
     * Removes a specific package from the buffer.
     * Search takes O(N) time but deletion is O(1).
     * @param pkg The package to remove.
     */
    public void removePackage(PackageEntity pkg) {
        DLLNode current = head;
        while (current != null) {
            if (current.data.equals(pkg)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next; // Removing head
                }
                
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev; // Removing tail
                }
                return;
            }
            current = current.next;
        }
    }
}
