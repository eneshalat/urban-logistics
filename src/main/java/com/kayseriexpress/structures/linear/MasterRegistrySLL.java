package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

/**
 * Singly Linked List for the Master Registry.
 * Maintains an immutable daily log of operations.
 */
public class MasterRegistrySLL {
    private Node head;
    private Node tail;

    /**
     * Initializes an empty master registry.
     */
    public MasterRegistrySLL() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds a package record to the tail in O(1) time.
     * @param pkg The package to record.
     */
    public void addRecord(PackageEntity pkg) {
        Node newNode = new Node(pkg);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Displays the audit log. Takes O(N) time.
     */
    public void displayLog() {
        System.out.println("--- Master Registry Audit Log ---");
        Node current = head;
        while (current != null) {
            System.out.println(current.data.toString());
            current = current.next;
        }
        System.out.println("---------------------------------");
    }
}
