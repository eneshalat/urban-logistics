package com.kayseriexpress.structures.graph;

public class MSTEngine {
    public static void calculateMST(CityMapGraph graph) {
        int vCount = 0;
        VertexNode curr = graph.head;
        while (curr != null) {
            vCount++;
            curr = curr.next;
        }

        if (vCount == 0) return;

        String[] names = new String[vCount];
        int[] key = new int[vCount];
        boolean[] inMST = new boolean[vCount];
        int[] parent = new int[vCount];

        curr = graph.head;
        int idx = 0;
        while (curr != null) {
            names[idx] = curr.name;
            key[idx] = Integer.MAX_VALUE;
            inMST[idx] = false;
            parent[idx] = -1;
            curr = curr.next;
            idx++;
        }

        // Start with first vertex
        key[0] = 0;

        for (int i = 0; i < vCount - 1; i++) {
            int u = minKey(key, inMST, vCount);
            if (u == -1) break;
            inMST[u] = true;

            VertexNode uNode = getVertexByName(graph, names[u]);
            Edge e = uNode.edgeHead;
            while (e != null) {
                int v = getIndexByName(names, e.destination, vCount);
                if (!inMST[v] && e.weight < key[v]) {
                    parent[v] = u;
                    key[v] = e.weight;
                }
                e = e.next;
            }
        }

        printMST(names, parent, key, vCount);
    }

    private static int minKey(int[] key, boolean[] inMST, int vCount) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < vCount; v++) {
            if (!inMST[v] && key[v] <= min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static int getIndexByName(String[] names, String name, int count) {
        for (int i = 0; i < count; i++) {
            if (names[i].equals(name)) return i;
        }
        return -1;
    }

    private static VertexNode getVertexByName(CityMapGraph graph, String name) {
        VertexNode curr = graph.head;
        while (curr != null) {
            if (curr.name.equals(name)) return curr;
            curr = curr.next;
        }
        return null;
    }

    private static void printMST(String[] names, int[] parent, int[] key, int vCount) {
        System.out.println("Minimum Spanning Tree (Prim's Algorithm):");
        int totalWeight = 0;
        for (int i = 1; i < vCount; i++) {
            if (parent[i] != -1) {
                System.out.println(names[parent[i]] + " - " + names[i] + " \tWeight: " + key[i]);
                totalWeight += key[i];
            }
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }
}
