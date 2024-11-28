import java.util.HashMap;
import java.util.Map;

class LRUCache {
    DLinkedList head = null;
    DLinkedList tail = null;
    int capacity = 0;
    Map<Integer, DLinkedList> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        DLinkedList node = map.getOrDefault(key, null);
        if (node == null)
            return -1;

        // Move the accessed node to the end (most recently used)
        remove(node);
        addToTail(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Key exists; update value and move to the end
            DLinkedList node = map.get(key);
            node.value = value;
            remove(node);
            addToTail(node);
        } else {
            // Key doesn't exist; create a new node
            if (map.size() == capacity) {
                // Evict the least recently used (head node)
                map.remove(head.key);
                remove(head);
            }
            DLinkedList newNode = new DLinkedList(key, value);
            addToTail(newNode);
            map.put(key, newNode);
        }
    }

    private void remove(DLinkedList node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            // Node is head
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            // Node is tail
            tail = node.prev;
        }
        node.prev = null;
        node.next = null;
    }

    private void addToTail(DLinkedList node) {
        if (tail == null) {
            // List is empty
            head = tail = node;
        } else {
            // Add to end of the list
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void traverse() {
        DLinkedList n = head;
        while (n != null) {
            System.out.print(n.prev + "<--[" + n.key + "," + n.value + "]-->" + n.next);
            n = n.next;
        }
        System.out.println();
    }
}

class DLinkedList {
    DLinkedList prev;
    DLinkedList next;
    int key;
    int value;

    public DLinkedList(int k, int v) {
        this.key = k;
        this.value = v;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */