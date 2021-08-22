package week09.classProblem;

import java.util.HashSet;

public class IsValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        HashSet[] row = new HashSet[9];
        HashSet[] col = new HashSet[9];
        HashSet[] box = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            row[i] = new HashSet<Character>();
            col[i] = new HashSet<Character>();
            box[i] = new HashSet<Character>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int boxId = i / 3 * 3 + j / 3;
                    char num = board[i][j];
                    if (row[i].contains(num)) {
                        return false;
                    }
                    if (col[j].contains(num)) {
                        return false;
                    }
                    if (box[boxId].contains(num)) {
                        return false;
                    }
                    row[i].add(num);
                    col[j].add(num);
                    box[boxId].add(num);
                }
            }
        }
        return true;
    }
}
