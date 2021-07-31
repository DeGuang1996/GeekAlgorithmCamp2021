package week07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindWords {

    class Node {
        public String word;
        public HashMap<Character, Node> child;

        public Node() {
            word = null;
            child = new HashMap<>();
        }
    }

    class Trie {
        public Node root;
        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node cur = root;
            for (char ch : word.toCharArray()) {
                if (!cur.child.containsKey(ch)) {
                    cur.child.put(ch, new Node());
                }
                cur = cur.child.get(ch);
            }
            cur.word = word;
        }
    }

    private List<String> ans = new ArrayList<>();
    private Trie trie = new Trie();
    private boolean[][] visited;
    private int m;
    private int n;
    private int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            trie.insert(word);
        }
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, trie.root);
            }
        }
        return ans;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private void dfs(char[][] board, int x, int y, Node cur) {
        if (!visited[x][y] && isValid(x, y)) {
            if (!cur.child.containsKey(board[x][y])) {
                return;
            }
            Node father = cur;
            cur = cur.child.get(board[x][y]);
            if (cur.word != null) {
                ans.add(cur.word);
                cur.word = null;
            }
            if (cur.child.isEmpty()) {
                father.child.remove(board[x][y]);
            }
            visited[x][y] = true;
            for (int i = 0; i < dir.length; i++) {
                int newX = x + dir[i][0];
                int newY = y + dir[i][1];
                if (isValid(newX, newY) && cur.child.containsKey(board[newX][newY])) {
                    dfs(board, newX, newY, cur);
                }
            }
            visited[x][y] = false;
        }
    }
}
