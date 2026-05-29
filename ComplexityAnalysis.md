# Urban Logistics System - Complexity Analysis Report
**Company:** Kayseri Express Logistics - Meydan Hub
**Mission:** Delivering the heart of Kayseri from Meydan to your doorstep with speed and reliability.

## 1. Linear Data Structures (Warehouse Operations)

### Master Registry (Singly Linked List)
The Master Registry maintains an immutable daily log of operations.
- **`addRecord(Package pkg)` - O(1)**
  - *Justification:* A tail pointer is maintained. Adding a node directly to the tail avoids the need to traverse the entire list, resulting in constant time complexity.
- **`displayLog()` - O(N)**
  - *Justification:* To print the audit log, every node from head to tail must be visited exactly once, where N is the total number of packages processed.

### Intake Buffer (Doubly Linked List)
The Intake Buffer handles incoming streams requiring rapid addition and removal.
- **`insertAtTail(Package pkg)` - O(1)**
  - *Justification:* Similar to the SLL, the DLL maintains a tail pointer. Constructing a new node, updating the previous and next pointers at the tail, and updating the tail reference are all constant time operations.
- **`removePackage(Package pkg)` - O(N)**
  - *Justification:* Searching for a specific package requires traversing from the head. Deletion is O(1), but the search takes O(N).
- **`removeFromHead()` - O(1)**
  - *Justification:* Directly removing the head node simply involves updating the head reference to `head.next` and clearing the new head's `prev` pointer.

## 2. Linear Data Structures (Dispatch Operations)

### Standard Delivery (Queue - FIFO)
Simulates the sorting station where first in is first out.
- **`enqueue(Package pkg)` - O(1)**
  - *Justification:* Handled by adding to the tail pointer of a linked structure.
- **`dequeue()` - O(1)**
  - *Justification:* Handled by removing from the head pointer of the linked structure.

### Truck Loading (Stack - LIFO)
Simulates narrow cargo spaces where last loaded is first out.
- **`push(Package pkg)` - O(1)**
  - *Justification:* Inserts a node directly at the top (head) of the stack.
- **`pop()` - O(1)**
  - *Justification:* Removes the node directly from the top (head) of the stack.

## 3. Hierarchical Data Structures (Database)

### Address Directory (AVL Tree)
Maps Kayseri neighborhoods to customer IDs and maintains balance to ensure rapid lookups.
- **`insert(String neighborhood, String customerID)` - O(log N)**
  - *Justification:* Since the tree self-balances (maintaining a strict height constraint), locating the correct leaf for insertion takes logarithmic time. The rotations (`rotateLeft`, `rotateRight`) that follow take O(1) time.
- **`search(String neighborhood)` - O(log N)**
  - *Justification:* The maximum depth of the tree is bounded by O(log N). Traversing down the tree comparing string values yields logarithmic time complexity.
- **`balance()`, `rotateLeft()`, `rotateRight()` - O(1)**
  - *Justification:* Rotations simply involve swapping 2-3 pointer references and recalculating node heights, independent of tree size.

## 4. Graph Algorithms (City Optimization)

### Graph Representation (Adjacency List via Custom Linked Lists)
- **`addEdge(String source, String destination, int weight)` - O(V)**
  - *Justification:* To avoid Java Collections, an array/linked list of vertices is scanned to find the correct `VertexNode` before pushing the edge into its linked list. In the worst-case, finding the source/destination takes O(V).

### Routing Optimization
- **`calculateShortestPath` (Dijkstra's Algorithm) - O(V^2)**
  - *Justification:* Since we are restricted from using `java.util.PriorityQueue`, the algorithm relies on scanning an unvisited vertex array to find the minimum distance node (`minDistance()`). This takes O(V) time and runs V times, leading to an O(V^2) bound. Edge relaxation takes O(E) globally. Total: O(V^2 + E) = O(V^2).
- **`calculateMST` (Prim's Algorithm) - O(V^2)**
  - *Justification:* Similar to Dijkstra's, finding the minimum key vertex takes O(V) for V iterations, summing to O(V^2) due to the absence of an optimized Min-Heap structure.
