package week07;

public class Solve {

    int m;
    int n;
    int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int getIndex(int i, int j) {
        return i * n + j;
    }

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
                    if (grid[i][j] == 'O') {
                        int idx = getIndex(i, j);
                        parents[idx] = idx;
                        count++;
                    }
                }
            }
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

    private void addUnionFind(char[][] grid, UnionFind unionFind, int x, int y) {
        for (int i = 0; i < dirs.length; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (isValid(newX, newY) && grid[newX][newY] == 'O') {
                grid[newX][newY] = 'P';
                unionFind.union(getIndex(x, y), getIndex(newX, newY));
                addUnionFind(grid, unionFind, newX, newY);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private boolean isEdge(int index) {
        int x = index / n;
        int y = index % n;
        return x == 0 || y == 0 || x == m - 1 || y == n - 1;
    }

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        UnionFind unionFind = new UnionFind(board);
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = 'P';
                addUnionFind(board, unionFind, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                board[i][n - 1] = 'P';
                addUnionFind(board, unionFind, i, n - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = 'P';
                addUnionFind(board, unionFind, 0, i);
            }
            if (board[m - 1][i] == 'O') {
                board[m - 1][i] = 'P';
                addUnionFind(board, unionFind, m - 1, i);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'P' || (board[i][j] == 'O' && isEdge(unionFind.find(getIndex(i, j))))) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // dfs解法
    // private void dfs(char[][] grid, int x, int y) {
    //     for (int i = 0; i < dirs.length; i++) {
    //         int newX = x + dirs[i][0];
    //         int newY = y + dirs[i][1];
    //         if (isValid(newX, newY) && grid[newX][newY] == 'O') {
    //             grid[newX][newY] = 'P';
    //             dfs(grid, newX, newY);
    //         }
    //     }
    // }
    //
    // public void solve(char[][] board) {
    //     m = board.length;
    //     n = board[0].length;
    //     for (int i = 0; i < m; i++) {
    //         if (board[i][0] == 'O') {
    //             board[i][0] = 'P';
    //             dfs(board, i, 0);
    //         }
    //         if (board[i][n - 1] == 'O') {
    //             board[i][n - 1] = 'P';
    //             dfs(board, i, n - 1);
    //         }
    //     }
    //     for (int i = 0; i < n; i++) {
    //         if (board[0][i] == 'O') {
    //             board[0][i] = 'P';
    //             dfs(board, 0, i);
    //         }
    //         if (board[m - 1][i] == 'O') {
    //             board[m - 1][i] = 'P';
    //             dfs(board, m - 1, i);
    //         }
    //     }
    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (board[i][j] == 'P') {
    //                 board[i][j] = 'O';
    //             } else {
    //                 board[i][j] = 'X';
    //             }
    //         }
    //     }
    // }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        Solve solve = new Solve();
        solve.solve(board);
    }
}
