package com.kayseriexpress.structures.graph;

public class DijkstraEngine {
    public static void calculateShortestPath(CityMapGraph graph, String start, String end) {
        int vCount = 0;
        VertexNode curr = graph.head;
        while (curr != null) {
            vCount++;
            curr = curr.next;
        }

        if (vCount == 0) return;

        String[] names = new String[vCount];
        int[] dist = new int[vCount];
        boolean[] visited = new boolean[vCount];
        int[] parent = new int[vCount];

        curr = graph.head;
        int idx = 0;
        int startIdx = -1;
        int endIdx = -1;

        while (curr != null) {
            names[idx] = curr.name;
            dist[idx] = Integer.MAX_VALUE;
            visited[idx] = false;
            parent[idx] = -1;
            if (curr.name.equals(start)) startIdx = idx;
            if (curr.name.equals(end)) endIdx = idx;
            curr = curr.next;
            idx++;
        }

        if (startIdx == -1 || endIdx == -1) {
            System.out.println("Start or End node not found.");
            return;
        }

        dist[startIdx] = 0;

        for (int i = 0; i < vCount - 1; i++) {
            int u = minDistance(dist, visited, vCount);
            if (u == -1) break;
            visited[u] = true;

            VertexNode uNode = getVertexByName(graph, names[u]);
            Edge e = uNode.edgeHead;
            while (e != null) {
                int v = getIndexByName(names, e.destination, vCount);
                if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]) {
                    dist[v] = dist[u] + e.weight;
                    parent[v] = u;
                }
                e = e.next;
            }
        }

        printPath(names, parent, dist, startIdx, endIdx);
    }

    private static int minDistance(int[] dist, boolean[] visited, int vCount) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < vCount; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
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

    private static void printPath(String[] names, int[] parent, int[] dist, int startIdx, int endIdx) {
        if (dist[endIdx] == Integer.MAX_VALUE) {
            System.out.println("No path from " + names[startIdx] + " to " + names[endIdx]);
            return;
        }
        System.out.println("Shortest path from " + names[startIdx] + " to " + names[endIdx] + " is " + dist[endIdx] + " km");
        System.out.print("Path: ");
        printPathRec(names, parent, endIdx);
        System.out.println();
    }

    private static void printPathRec(String[] names, int[] parent, int currentIdx) {
        if (currentIdx == -1) return;
        printPathRec(names, parent, parent[currentIdx]);
        System.out.print(names[currentIdx] + " ");
    }
}
