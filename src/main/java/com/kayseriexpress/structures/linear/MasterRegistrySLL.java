package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

public class MasterRegistrySLL {
    private Node head;
    private Node tail;

    public MasterRegistrySLL() {
        this.head = null;
        this.tail = null;
    }

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
