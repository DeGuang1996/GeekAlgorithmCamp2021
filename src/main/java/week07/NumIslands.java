package week07;

public class NumIslands {

    int m;
    int n;
    int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    class UnionFind {
        int count;
        int[] parents;
        int[] rank;

        public UnionFind(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            count = 0;
            parents = new int[m * n];
            rank = new int[m * n];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1') {
                        int idx = getIndex(i, j);
                        parents[idx] = idx;
                        count++;
                    }
                }
            }
        }

        public int getIndex(int i, int j) {
            return i * n + j;
        }

        public int find(int i) {
            if (parents[i] != i) {
                parents[i] = find(parents[i]);
            }
            return parents[i];
        }

        public void union(int i, int j) {
            int root1 = find(i);
            int root2 = find(j);
            if (root1 != root2) {
                if (rank[root1] > rank[root2]) {
                    parents[root2] = parents[root1];
                } else if (rank[root2] > rank[root1]) {
                    parents[root1] = parents[root2];
                } else {
                    parents[root2] = parents[root1];
                    rank[root1]++;
                }
                count--;
            }
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    addUnionFind(grid, unionFind, i, j);
                }
            }
        }
        return unionFind.count;
    }

    private void addUnionFind(char[][] grid, UnionFind unionFind, int x, int y) {
        for (int i = 0; i < dirs.length; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (isValid(newX, newY) && grid[newX][newY] == '1') {
                unionFind.union(unionFind.getIndex(x, y), unionFind.getIndex(newX, newY));
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
