package week07;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FindCircleNum {

    public int findCircleNum(int[][] isConnected) {
        // dfs解法
        // int ans = 0;
        // boolean[] visited = new boolean[isConnected.length];
        // for (int i = 0; i < isConnected.length; i++) {
        //     if (!visited[i]) {
        //         dfs(isConnected, visited, i);
        //         ans++;
        //     }
        // }
        // return ans;

        // bfs解法
        // int ans = 0;
        // boolean[] visited = new boolean[isConnected.length];
        // Deque<Integer> deque = new ArrayDeque<>();
        // for (int i = 0; i < isConnected.length; i++) {
        //     if (!visited[i]) {
        //         ans++;
        //         deque.offerFirst(i);
        //         while (!deque.isEmpty()) {
        //             int cur = deque.peekFirst();
        //             visited[cur] = true;
        //             for (int j = 0; j < isConnected[cur].length; j++) {
        //                 if (j != cur && isConnected[cur][j] == 1 && !visited[j]) {
        //                     deque.offerLast(j);
        //                 }
        //             }
        //             deque.pollFirst();
        //         }
        //     }
        // }
        // return ans;

        // union find 解法
        int ans = 0;
        int[] parents = new int[isConnected.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    union(parents, i, j);
                }
            }
        }
        for (int i = 0; i < parents.length; i++) {
            if (find(parents, i) == i) {
                ans++;
            }
        }
        return ans;
    }

    private void union(int[] parents, int i, int j) {
        parents[find(parents, i)] = find(parents, j);
    }

    private int find(int[] parents, int i) {
        if (parents[i] != i) {
            parents[i] = find(parents, parents[i]);
        }
        return parents[i];
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (int j = 0; j < isConnected[i].length; j++) {
            if (j != i && isConnected[i][j] == 1) {
                dfs(isConnected, visited, j);
            }
        }
    }
}
