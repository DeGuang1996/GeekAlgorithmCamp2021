package week09.classProblem;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {

    private boolean[] visited;
    private int n;
    private List<List<String>> res = new ArrayList<>();
    private List<String> temp = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> board = new ArrayList<>();

    private String getLine(int pos, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append('.');
        }
        stringBuilder.setCharAt(pos, 'Q');
        return stringBuilder.toString();
    }

    private void addPoint(int x, int y) {
        ArrayList<Integer> point = new ArrayList<>();
        point.add(x);
        point.add(y);
        board.add(point);
    }

    private boolean isSafe(int x, int y) {
        for (ArrayList<Integer> past : board) {
            int preX = past.get(0);
            int preY = past.get(1);
            if (x == preX || y == preY || Math.abs(x - preX) == Math.abs(y - preY)) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int cur) {
        if (cur == n) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && isSafe(cur, i)) {
                temp.add(getLine(i, n));
                addPoint(cur, i);
                visited[i] = true;
                dfs(cur + 1);
                visited[i] = false;
                board.remove(board.size() - 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        visited = new boolean[n];
        this.n = n;
        dfs(0);
        return res;
    }
}
