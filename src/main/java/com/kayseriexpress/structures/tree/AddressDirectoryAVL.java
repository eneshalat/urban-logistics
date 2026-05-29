package com.kayseriexpress.structures.tree;

public class AddressDirectoryAVL {
    private AVLNode root;

    public AddressDirectoryAVL() {
        this.root = null;
    }

    private int height(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }

    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private AVLNode balance(AVLNode node) {
        int balanceFact = getBalance(node);

        // Left Left Case
        if (balanceFact > 1 && getBalance(node.left) >= 0)
            return rotateRight(node);

        // Right Right Case
        if (balanceFact < -1 && getBalance(node.right) <= 0)
            return rotateLeft(node);

        // Left Right Case
        if (balanceFact > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left Case
        if (balanceFact < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void insert(String neighborhood, String customerID) {
        root = insertRec(root, neighborhood, customerID);
    }

    private AVLNode insertRec(AVLNode node, String neighborhood, String customerID) {
        if (node == null)
            return new AVLNode(neighborhood, customerID);

        int cmp = neighborhood.compareTo(node.neighborhood);
        if (cmp < 0)
            node.left = insertRec(node.left, neighborhood, customerID);
        else if (cmp > 0)
            node.right = insertRec(node.right, neighborhood, customerID);
        else {
            // Duplicate neighborhoods can either update or we can ignore
            // We'll update the customerID here
            node.customerID = customerID;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        return balance(node);
    }

    public String search(String neighborhood) {
        AVLNode res = searchRec(root, neighborhood);
        if (res != null) {
            return res.customerID;
        }
        return null;
    }

    private AVLNode searchRec(AVLNode node, String neighborhood) {
        if (node == null || node.neighborhood.equals(neighborhood))
            return node;

        if (node.neighborhood.compareTo(neighborhood) > 0)
            return searchRec(node.left, neighborhood);

        return searchRec(node.right, neighborhood);
    }
}
