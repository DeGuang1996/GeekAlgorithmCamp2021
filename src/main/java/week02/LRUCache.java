package week02;

import java.util.*;

// class LRUCache extends LinkedHashMap<Integer, Integer> {
//
//     private int capacity;
//
//     public LRUCache(int capacity) {
//         super(capacity, 0.75f, true);
//         this.capacity = capacity;
//     }
//
//     public int get(int key) {
//         return super.getOrDefault(key, -1);
//     }
//
//     public void put(int key, int value) {
//         super.put(key, value);
//     }
//
//     @Override
//     protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//         return size() > this.capacity;
//     }
// }

class LRUCache {

    class DLinkedNode {
        public int key;
        public int value;
        public DLinkedNode prev;
        public DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private int capacity;
    private HashMap<Integer, DLinkedNode> hashMap;
    private DLinkedNode head;
    private DLinkedNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            DLinkedNode node = hashMap.get(key);
            moveNodeToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            DLinkedNode node = hashMap.get(key);
            node.value = value;
            hashMap.put(key, node);
            moveNodeToHead(node);
        } else {
            DLinkedNode node = new DLinkedNode(key, value);
            hashMap.put(key, node);
            addNodeToTail(node);
            moveNodeToHead(node);
            if (hashMap.size() > capacity) {
                hashMap.remove(tail.prev.key);
                removeNode(tail.prev);
            }
        }
    }

    public void moveNodeToHead(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void addNodeToTail(DLinkedNode node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    public void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        lruCache.get(2);
    }
}
