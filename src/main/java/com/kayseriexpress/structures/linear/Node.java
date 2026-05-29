package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

public class Node {
    public PackageEntity data;
    public Node next;

    public Node(PackageEntity data) {
        this.data = data;
        this.next = null;
    }
}
