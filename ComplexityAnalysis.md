# Urban Logistics System - Complexity Analysis Report
**Company:** Kayseri Express Logistics - Meydan Hub
**Mission:** Delivering the heart of Kayseri from Meydan to your doorstep with speed and reliability.

## Complexity Analysis Table

| Category | Component | Operation | Time Complexity | Justification |
| :--- | :--- | :--- | :--- | :--- |
| Linear (Warehouse) | Master Registry (SLL) | `addRecord(Package pkg)` | O(1) | A tail pointer is maintained. Adding a node directly to the tail avoids traversing the entire list. |
| Linear (Warehouse) | Master Registry (SLL) | `displayLog()` | O(N) | Every node from head to tail must be visited exactly once to print the log. |
| Linear (Warehouse) | Intake Buffer (DLL) | `insertAtTail(Package pkg)` | O(1) | Maintains a tail pointer. Direct pointer updates ensure constant time complexity. |
| Linear (Warehouse) | Intake Buffer (DLL) | `removePackage(Package pkg)` | O(N) | Searching for a specific package requires traversing from the head. Deletion is O(1), but search takes O(N). |
| Linear (Warehouse) | Intake Buffer (DLL) | `removeFromHead()` | O(1) | Directly removing the head node simply involves updating the head reference. |
| Linear (Dispatch) | Standard Delivery (Queue) | `enqueue(Package pkg)` | O(1) | Handled by adding to the tail pointer of a linked structure. |
| Linear (Dispatch) | Standard Delivery (Queue) | `dequeue()` | O(1) | Handled by removing from the head pointer of the linked structure. |
| Linear (Dispatch) | Truck Loading (Stack) | `push(Package pkg)` | O(1) | Inserts a node directly at the top (head) of the stack. |
| Linear (Dispatch) | Truck Loading (Stack) | `pop()` | O(1) | Removes the node directly from the top (head) of the stack. |
| Hierarchical (DB) | Address Directory (AVL) | `insert(neighborhood, ID)` | O(log N) | Self-balancing tree limits height to O(log N). Traversing takes logarithmic time; rotations take O(1). |
| Hierarchical (DB) | Address Directory (AVL) | `search(neighborhood)` | O(log N) | The maximum depth is bounded by O(log N). Traversing string values yields logarithmic time. |
| Hierarchical (DB) | Address Directory (AVL) | `balance()`, `rotate...()` | O(1) | Rotations involve swapping a few pointer references and recalculating heights. |
| Graph Algorithms | Graph Representation | `addEdge(src, dest, weight)` | O(V) | Scanning an array/linked list of vertices to find the correct VertexNode takes O(V) in worst-case. |
| Graph Algorithms | Routing Optimization | `calculateShortestPath` | O(V^2) | Dijkstra using array scan for minDistance takes O(V) for V iterations. Total O(V^2). |
| Graph Algorithms | Routing Optimization | `calculateMST` | O(V^2) | Prim's using array scan for min key vertex takes O(V) for V iterations. |

