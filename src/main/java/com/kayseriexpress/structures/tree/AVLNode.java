package com.kayseriexpress.structures.tree;

public class AVLNode {
    public String neighborhood;
    public String customerID;
    public int height;
    public AVLNode left;
    public AVLNode right;

    public AVLNode(String neighborhood, String customerID) {
        this.neighborhood = neighborhood;
        this.customerID = customerID;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
