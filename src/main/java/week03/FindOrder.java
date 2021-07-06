package week03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindOrder {

    ArrayList<ArrayList<Integer>> edges;
    List<Integer> visited;
    ArrayList<Integer> res;
    boolean valid = true;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        visited = new ArrayList<>(numCourses);
        res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
            visited.add(0);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            ArrayList<Integer> edge = edges.get(prerequisites[i][1]);
            edge.add(prerequisites[i][0]);
        }
        for (int i = 0; i < edges.size(); i++) {
            if (visited.get(i) == 0) {
                dfs(i);
            }
            if (!valid) {
                break;
            }
        }
        if (!valid) {
            return new int[0];
        }
        Collections.reverse(res);
        return res.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(int i) {
        visited.set(i, 1);
        ArrayList<Integer> next = edges.get(i);
        for (int j = 0; j < next.size(); j++) {
            if (visited.get(next.get(j)) == 0) {
                dfs(next.get(j));
                if (!valid) {
                    return;
                }
            } else if (visited.get(next.get(j)) == 1) {
                valid = false;
                return;
            }
        }
        visited.set(i, 2);
        res.add(i);
    }
}
