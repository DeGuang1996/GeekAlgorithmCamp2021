package week03;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class LongestIncreasingPath {

    // DFS + 记忆化搜索
    // private int rows;
    // private int cols;
    // private HashMap<Integer, Integer> hashMap = new HashMap<>();
    // public int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    //
    // public int longestIncreasingPath(int[][] matrix) {
    //     int ans = 1;
    //     rows = matrix.length;
    //     cols = matrix[0].length;
    //     for (int i = 0; i < matrix.length; i++) {
    //         for (int j = 0; j < matrix[i].length; j++) {
    //             ans = Math.max(ans, dfs(i, j, matrix, matrix[i][j]));
    //         }
    //     }
    //     return ans;
    // }
    //
    // private int dfs(int x, int y, int[][] matrix, int preValue) {
    //     if (x < 0 || x >= rows || y < 0 || y >= cols) {
    //         return 0;
    //     }
    //     if (hashMap.get(x * cols + y) != null) {
    //         return hashMap.get(x * cols + y);
    //     }
    //     int count = 0;
    //     for (int i = 0; i < dirs.length; i++) {
    //         int nextX = x + dirs[i][0];
    //         int nextY = y + dirs[i][1];
    //         if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {
    //             continue;
    //         }
    //         if (preValue < matrix[nextX][nextY]) {
    //             count = Math.max(count, dfs(nextX, nextY, matrix, matrix[nextX][nextY]));
    //         }
    //     }
    //     hashMap.put(x * cols + y, count + 1);
    //     return count + 1;
    // }

    // BFS + topSort

    private int rows;
    private int cols;
    private int ans;
    private int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int[] inDegree;
    private int[] distance;
    private ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

    public int longestIncreasingPath(int[][] matrix) {
        init(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int k = 0; k < dirs.length; k++) {
                    if (isValid(i, j, k, matrix)) {
                        int nextX = i + dirs[k][0];
                        int nextY = j + dirs[k][1];
                        inDegree[getIndex(nextX, nextY)]++;
                        edges.get(getIndex(i, j)).add(getIndex(nextX, nextY));
                    }
                }
            }
        }
        topSort();
        return ans;
    }

    private void init(int[][] matrix) {
        rows = matrix.length;
        cols = matrix[0].length;
        ans = 1;
        inDegree = new int[rows * cols];
        distance = new int[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                edges.add(new ArrayList<>());
            }
        }
    }

    private int getIndex(int x, int y) {
        return x * cols + y;
    }

    private boolean isValid(int x, int y, int k, int[][] matrix) {
        int nextX = x + dirs[k][0];
        int nextY = y + dirs[k][1];
        if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {
            return false;
        }
        return matrix[x][y] < matrix[nextX][nextY];
    }

    private void topSort() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                arrayDeque.add(i);
                distance[i] = 1;
            }
        }
        while (!arrayDeque.isEmpty()) {
            int cur = arrayDeque.pollLast();
            for (int next : edges.get(cur)) {
                distance[next] = Math.max(distance[next], distance[cur] + 1);
                ans = Math.max(ans, distance[next]);
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    arrayDeque.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{7,7,5},{2,4,6},{8,2,0}};
        LongestIncreasingPath longestIncreasingPath = new LongestIncreasingPath();
        longestIncreasingPath.longestIncreasingPath(matrix);
    }
}
