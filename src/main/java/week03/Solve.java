package week03;

public class Solve {

    private int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '2') {
                    mark(board, i, j);
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
            return;
        }
        if (board[x][y] == 'X' || board[x][y] == '1' || board[x][y] == '2') {
            return;
        }
        board[x][y] = '1';
        for (int i = 0; i < dirs.length; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[newX].length) {
                board[x][y] = '2';
                continue;
            }
            dfs(board, newX, newY);
        }
    }

    private void mark(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
            return;
        }
        if (board[x][y] == 'X') {
            return;
        }
        board[x][y] = 'O';
        for (int i = 0; i < dirs.length; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[newX].length) {
                continue;
            }
            mark(board, newX, newY);
        }
    }
}
