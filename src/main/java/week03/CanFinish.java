package week03;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class CanFinish {

    private int[] inDegree;
    private ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            edges.get(edge[1]).add(edge[0]);
            inDegree[edge[0]]++;
        }
        return topSort() == numCourses;
    }

    private int topSort() {
        int ans = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                deque.add(i);
                ans++;
            }
        }
        while (!deque.isEmpty()) {
            int cur = deque.pollLast();
            for (int next : edges.get(cur)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    deque.add(next);
                    ans++;
                }
            }
        }
        return ans;
    }
}
