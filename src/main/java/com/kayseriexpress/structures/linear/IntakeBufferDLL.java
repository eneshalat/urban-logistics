package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

public class IntakeBufferDLL {
    private DLLNode head;
    private DLLNode tail;

    public IntakeBufferDLL() {
        this.head = null;
        this.tail = null;
    }

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
