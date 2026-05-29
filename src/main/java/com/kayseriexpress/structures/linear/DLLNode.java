package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;

public class DLLNode {
    public PackageEntity data;
    public DLLNode prev;
    public DLLNode next;

    public DLLNode(PackageEntity data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
