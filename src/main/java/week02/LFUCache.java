package week02;

import java.util.HashMap;
import java.util.TreeSet;

class LFUCache {

    class Node implements Comparable<Node> {
        public int count;
        public int time;
        public int key;
        public int value;

        @Override
        public int compareTo(Node node) {
            if (node.count == this.count) {
                return node.time - this.time;
            }
            return node.count - this.count;
        }

        public Node(int count, int time, int key, int value) {
            this.count = count;
            this.time = time;
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private int time;
    private HashMap<Integer, Node> hashMap;
    private TreeSet<Node> treeSet;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        this.hashMap = new HashMap<>();
        this.treeSet = new TreeSet<>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (hashMap.containsKey(key)) {
            time++;
            Node node = hashMap.get(key);
            treeSet.remove(node);
            node.time = time;
            node.count++;
            treeSet.add(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        time++;
        Node node;
        if (hashMap.containsKey(key)) {
            node = hashMap.get(key);
            treeSet.remove(node);
            node.value = value;
            node.time = time;
            node.count++;
        } else {
            node = new Node(1, time, key, value);
            if (hashMap.size() >= capacity) {
                Node deleted = treeSet.last();
                hashMap.remove(deleted.key);
                treeSet.remove(deleted);
            }
            hashMap.put(key, node);
        }
        treeSet.add(node);
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.get(1);
        lfuCache.put(3, 3);
        lfuCache.get(3);
        lfuCache.put(4, 4);
        lfuCache.get(1);
        lfuCache.get(3);
        lfuCache.get(4);
    }
}
