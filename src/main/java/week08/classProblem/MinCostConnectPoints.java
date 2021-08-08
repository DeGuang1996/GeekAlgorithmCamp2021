package week08.classProblem;

import java.util.ArrayList;
import java.util.List;

public class MinCostConnectPoints {

    private int[] parents;

    public int minCostConnectPoints(int[][] points) {
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edges.add(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});
            }
        }
        edges.sort((term1, term2) -> term1[2] - term2[2]);
        // Kruskal
        parents = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            parents[i] = i;
        }
        int ans = 0;
        for (int i = 0; i < edges.size(); i++) {
            int from = edges.get(i)[0];
            int to = edges.get(i)[1];
            int cost = edges.get(i)[2];
            if (find(from) != find(to)) {
                ans += cost;
                parents[find(from)] = find(to);
            }
        }
        return ans;
    }

    private int find(int x) {
        if (x != parents[x]) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }
}
