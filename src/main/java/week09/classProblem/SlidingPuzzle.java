package week09.classProblem;

import java.util.*;

public class SlidingPuzzle {

    private int m;
    private int n;
    private Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(term -> term[0]));
    private Map<Integer, Integer> dist = new HashMap<>();
    private int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int slidingPuzzle(int[][] board) {
        m = board.length;
        n = board[0].length;
        int target = 0;
        for (int i = 1; i <= m * n; i++) {
            target = target * 10 + i % (m * n);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(board[i][j]);
            }
        }
        int start = zip(list);
        queue.offer(new int[]{evaluate(list), start});
        dist.put(start, 0);

        while (!queue.isEmpty()) {
            int now = queue.poll()[1];
            ArrayList<Integer> nowList = unZip(now);
            int pos = getZeroIndex(nowList);
            int curX = pos % n;
            int curY = pos / n;
            for (int i = 0; i < dirs.length; i++) {
                int nextX = curX + dirs[i][0];
                int nextY = curY + dirs[i][1];
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                    int nextPos = nextY * n + nextX;
                    insert(pos, nextPos, nowList, now);
                }
            }
            if (dist.containsKey(target)) {
                return dist.get(target);
            }
        }
        return -1;
    }

    private void insert(int pos, int newPos, ArrayList<Integer> list, int now) {
        Collections.swap(list, pos, newPos);
        int next = zip(list);
        if (!dist.containsKey(next)) {
            dist.put(next, dist.get(now) + 1);
            queue.offer(new int[]{dist.get(next) + evaluate(list), next});
        }
        Collections.swap(list, pos, newPos);
    }

    private int getZeroIndex(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                return i;
            }
        }
        return -1;
    }

    private int zip(ArrayList<Integer> list) {
        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            ans = ans * 10 + list.get(i);
        }
        return ans;
    }

    private ArrayList<Integer> unZip(int state) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < m * n; i++) {
            list.add(state % 10);
            state /= 10;
        }
        Collections.reverse(list);
        return list;
    }

    private int evaluate(ArrayList<Integer> list) {
        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                continue;
            }
            int curX = i % n;
            int curY = i / n;
            int currPos = list.get(i) - 1;
            int corrX = currPos % n;
            int corrY = currPos / n;
            ans += Math.abs(corrX - curX) + Math.abs(corrY - curY);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] board = {{3,2,4},{1,5,0}};
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        slidingPuzzle.slidingPuzzle(board);
    }
}
