package week07;

// 数组实现
// class Trie {
//
//     private Trie[] next;
//     private boolean isEnd;
//
//     /** Initialize your data structure here. */
//     public Trie() {
//         next = new Trie[26];
//         isEnd = false;
//     }
//
//     /** Inserts a word into the trie. */
//     public void insert(String word) {
//         Trie cur = this;
//         for (int i = 0; i < word.length(); i++) {
//             int nextIdx = word.charAt(i) - 'a';
//             if (cur.next[nextIdx] == null) {
//                 cur.next[nextIdx] = new Trie();
//             }
//             cur = cur.next[nextIdx];
//         }
//         cur.isEnd = true;
//     }
//
//     /** Returns if the word is in the trie. */
//     public boolean search(String word) {
//         Trie cur = this;
//         for (int i = 0; i < word.length(); i++) {
//             int nextIdx = word.charAt(i) - 'a';
//             if (cur.next[nextIdx] == null) {
//                 return false;
//             }
//             cur = cur.next[nextIdx];
//         }
//         return cur.isEnd;
//     }
//
//     /** Returns if there is any word in the trie that starts with the given prefix. */
//     public boolean startsWith(String prefix) {
//         Trie cur = this;
//         for (int i = 0; i < prefix.length(); i++) {
//             int nextIdx = prefix.charAt(i) - 'a';
//             if (cur.next[nextIdx] == null) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }

import java.util.HashMap;

class Trie {

    class Node {
        public int count;
        public HashMap<Character, Node> child;

        public Node() {
            count = 0;
            child = new HashMap<>();
        }
    }

    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        operateOnTrie(word, true, false);
    }

    public boolean search(String word) {
        return operateOnTrie(word, false, false);
    }

    public boolean startsWith(String prefix) {
        return operateOnTrie(prefix, false, true);
    }

    public boolean operateOnTrie(String word, boolean insertIfNotExist, boolean searchPrefix) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            if (!cur.child.containsKey(ch)) {
                if (insertIfNotExist) {
                    cur.child.put(ch, new Node());
                } else {
                    return false;
                }
            }
            cur = cur.child.get(ch);
        }
        if (searchPrefix) {
            return true;
        }
        if (insertIfNotExist) {
            cur.count++;
        }
        return cur.count > 0;
    }
}
