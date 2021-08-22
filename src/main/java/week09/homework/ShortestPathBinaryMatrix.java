package week09.homework;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMatrix {

    private int[][] dirs = {{-1,0}, {-1,1}, {0,1},{1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        // BFS解法
        if (grid[0][0] == 1) {
            return -1;
        }
        int res = 0, n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] vis = new boolean[n][n];
        queue.offer(new int[]{0, 0});
        vis[0][0] = true;
        while (!queue.isEmpty()) {
            res++;
            int l = queue.size();
            for(int i = 0; i < l; i++) {
                int[] t = queue.poll();
                if (t[0] == n - 1 && t[1] == n - 1) {
                    return res;
                }
                for (int[] d : dirs) {
                    int x = t[0] + d[0], y = t[1] + d[1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 && !vis[x][y]) {
                        vis[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return -1;
    }
}
