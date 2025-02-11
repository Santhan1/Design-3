import java.util.HashMap;

class LRUCache {

    // Node class to represent each key-value pair in the doubly linked list
    class Node {
        int key, value;  // Stores the key and value of the cache entry
        Node prev, next; // Pointers to previous and next nodes in the list

        // Constructor to initialize a node with a key and value
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> map; // HashMap to store key-node mappings for O(1) access
    int capacity;  // Maximum capacity of the cache
    Node head, tail; // Dummy head and tail nodes for the doubly linked list

    /**
     * Adds a node right after the head (most recently used position)
     */
    private void addToHead(Node node) {
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;
    }

    /**
     * Removes a node from its current position in the linked list
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Constructor initializes the LRUCache with a given capacity
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1); // Dummy head node
        tail = new Node(-1, -1); // Dummy tail node
        head.next = tail;  // Connect head to tail initially
        tail.prev = head;  // Connect tail to head
    }

    /**
     * Retrieves the value of the key if it exists in the cache, otherwise returns -1
     * Moves the accessed node to the head (most recently used)
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1; // Key does not exist in cache
        }
        Node node = map.get(key);
        removeNode(node); // Remove from current position
        addToHead(node);  // Move to head (most recently used)
        return node.value;
    }

    /**
     * Inserts a key-value pair into the cache. If the key already exists, updates its value.
     * If the cache reaches its capacity, removes the least recently used item (from the tail).
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node); // Remove from current position
            addToHead(node);  // Move to head (most recently used)
            node.value = value; // Update value
            return;
        }

        // If the cache is at full capacity, remove the least recently used node (from the tail)
        if (map.size() == capacity) {
            Node tailPrev = tail.prev; // Node to remove (LRU node)
            removeNode(tailPrev);
            map.remove(tailPrev.key); // Remove from hashmap
        }

        // Insert the new node at the head (most recently used position)
        Node node = new Node(key, value);
        addToHead(node);
        map.put(key, node);
    }
}
