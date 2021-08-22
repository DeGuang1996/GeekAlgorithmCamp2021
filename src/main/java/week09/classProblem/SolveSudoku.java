package week09.classProblem;

import java.util.Arrays;

public class SolveSudoku {

    boolean[][] rowUsed;
    boolean[][] colUsed;
    boolean[][] boxUsed;

    public void solveSudoku(char[][] board) {
        rowUsed = new boolean[9][10];
        colUsed = new boolean[9][10];
        boxUsed = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(rowUsed[i], true);
            Arrays.fill(colUsed[i], true);
            Arrays.fill(boxUsed[i], true);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int boxId = i / 3 * 3 + j / 3;
                    rowUsed[i][num] = false;
                    colUsed[j][num] = false;
                    boxUsed[boxId][num] = false;
                }
            }
        }

        dfs(board);
    }

    private boolean dfs(char[][] board) {
        int[] location = getLocation(board);
        int x = location[0];
        int y = location[1];
        if (x == -1) {
            return true;
        }
        for (int num = 1; num < 10; num++) {
            int boxId = x / 3 * 3 + y / 3;
            if (rowUsed[x][num] && colUsed[y][num] && boxUsed[boxId][num]) {
                rowUsed[x][num] = colUsed[y][num] = boxUsed[boxId][num] = false;
                board[x][y] = (char) ('0' + num);
                if (dfs(board)) {
                    return true;
                }
                board[x][y] = '.';
                rowUsed[x][num] = colUsed[y][num] = boxUsed[boxId][num] = true;
            }
        }
        return false;
    }
    
    private int[] getLocation(char[][] board) {
        int ansCount = 10;
        int[] ans = new int[]{-1, -1};
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    int cnt = 0;
                    int boxId = i / 3 * 3 + j / 3;
                    for (int num = 1; num < 10; num++) {
                        if (rowUsed[i][num] && colUsed[j][num] && boxUsed[boxId][num]) {
                            cnt++;
                        }
                    }
                    if (cnt < ansCount) {
                        ansCount = cnt;
                        ans = new int[]{i, j};
                    }
                }
            }
        }
        return ans;
    }

}
