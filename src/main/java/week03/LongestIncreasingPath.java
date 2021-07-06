package week03;

import java.util.HashMap;

public class LongestIncreasingPath {

    private int rows;
    private int cols;
    private HashMap<Integer, Integer> hashMap = new HashMap<>();
    public int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int ans = 1;
        rows = matrix.length;
        cols = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                ans = Math.max(ans, dfs(i, j, matrix, matrix[i][j]));
            }
        }
        return ans;
    }

    private int dfs(int x, int y, int[][] matrix, int preValue) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return 0;
        }
        if (hashMap.get(x * cols + y) != null) {
            return hashMap.get(x * cols + y);
        }
        int count = 0;
        for (int i = 0; i < dirs.length; i++) {
            int nextX = x + dirs[i][0];
            int nextY = y + dirs[i][1];
            if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {
                continue;
            }
            if (preValue < matrix[nextX][nextY]) {
                count = Math.max(count, dfs(nextX, nextY, matrix, matrix[nextX][nextY]));
            }
        }
        hashMap.put(x * cols + y, count + 1);
        return count + 1;
    }
}
