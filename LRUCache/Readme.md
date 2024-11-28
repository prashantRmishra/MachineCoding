### LRU Cache Implementation

---

## **LRU Cache**

This repository contains a Java implementation of an **LRU Cache** (Least Recently Used Cache) using a combination of a **HashMap** and a **Doubly Linked List**. The design ensures efficient operations with a time complexity of \(O(1)\) for both `get` and `put`.

---

## **Features**

1. **Efficient Cache Management**:
   - Stores a fixed number of key-value pairs.
   - Evicts the least recently used item when the cache is full.

2. **Performance**:
   - **Get Operation**: Retrieves the value associated with a key in \(O(1)\).
   - **Put Operation**: Inserts or updates a key-value pair in \(O(1)\).

3. **Design**:
   - Uses a **doubly linked list** to maintain the order of usage.
   - Utilizes a **HashMap** for constant-time access to cache nodes.

---

## **Implementation Details**

### **Data Structures Used**
1. **Doubly Linked List**:
   - Tracks the order of access.
   - The head represents the least recently used (LRU) item.
   - The tail represents the most recently used (MRU) item.

2. **HashMap**:
   - Maps keys to their corresponding nodes in the doubly linked list for \(O(1)\) lookups.

---

## **Code Explanation**

### **Class: `LRUCache`**
This class defines the LRU Cache functionality.

- **Fields**:
  - `head`: Points to the least recently used node.
  - `tail`: Points to the most recently used node.
  - `capacity`: Maximum number of items the cache can store.
  - `map`: A HashMap for constant-time access to nodes.

- **Methods**:
  1. `LRUCache(int capacity)`: Constructor to initialize the cache with a given capacity.
  2. `int get(int key)`: Retrieves the value for a given key. If the key is not found, returns `-1`.
  3. `void put(int key, int value)`: Inserts or updates a key-value pair. Evicts the LRU item if the cache is full.
  4. `void remove(DLinkedList node)`: Removes a node from the doubly linked list.
  5. `void addToTail(DLinkedList node)`: Adds a node to the end of the doubly linked list.
  6. `void traverse()`: (Optional) Debugging utility to print the current state of the cache.

### **Class: `DLinkedList`**
This class defines the nodes used in the doubly linked list.

- **Fields**:
  - `prev`: Pointer to the previous node.
  - `next`: Pointer to the next node.
  - `key`: The key of the cache entry.
  - `value`: The value of the cache entry.

- **Constructor**:
  - `DLinkedList(int key, int value)`: Initializes a node with the given key and value.

---

## **Usage**

### **Example**
```java
public class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);   // Cache: {1=1}
        cache.put(2, 2);   // Cache: {1=1, 2=2}
        System.out.println(cache.get(1)); // Output: 1; Cache: {2=2, 1=1}
        cache.put(3, 3);   // Evicts 2; Cache: {1=1, 3=3}
        System.out.println(cache.get(2)); // Output: -1 (not found)
        cache.put(4, 4);   // Evicts 1; Cache: {3=3, 4=4}
        System.out.println(cache.get(1)); // Output: -1 (not found)
        System.out.println(cache.get(3)); // Output: 3; Cache: {4=4, 3=3}
        System.out.println(cache.get(4)); // Output: 4; Cache: {3=3, 4=4}
    }
}
```

### **Expected Output**
```plaintext
1
-1
-1
3
4
```
---

## **Complexity Analysis**

1. **Time Complexity**:
   - **`get(key)`**: \(O(1)\)
   - **`put(key, value)`**: \(O(1)\)

2. **Space Complexity**:
   - \(O(C)\), where \(C\) is the capacity of the cache.

---

## **Future Enhancements**

1. Add thread safety for concurrent usage.
2. Implement dynamic resizing of the cache.
3. Extend the implementation to support generic key-value types.

---