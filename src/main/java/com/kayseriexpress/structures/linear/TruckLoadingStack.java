package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

public class TruckLoadingStack {
    private Node top;

    public TruckLoadingStack() {
        this.top = null;
    }

    public void push(PackageEntity pkg) {
        Node newNode = new Node(pkg);
        newNode.next = top;
        top = newNode;
    }

    public PackageEntity pop() {
        if (top == null) {
            return null; // Stack is empty
        }
        
        PackageEntity pkg = top.data;
        top = top.next;
        return pkg;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
}
