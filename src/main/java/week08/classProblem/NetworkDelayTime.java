package week08.classProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        // dijkstra方法
        // HashMap<Integer, ArrayList<int[]>> edges = new HashMap<>();
        // for (int i = 0; i < times.length; i++) {
        //     int from = times[i][0];
        //     int to = times[i][1];
        //     int cost = times[i][2];
        //
        //     edges.putIfAbsent(from, new ArrayList<>());
        //     edges.get(from).add(new int[]{to, cost});
        // }
        // PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((term1, term2) -> term1[1] - term2[1]);
        // priorityQueue.offer(new int[]{k, 0});
        // HashMap<Integer, Integer> visited = new HashMap<>();
        // int ans = 0;
        //
        // while (!priorityQueue.isEmpty()) {
        //     if (visited.size() >= n) {
        //         break;
        //     }
        //     int[] cur = priorityQueue.poll();
        //     int from = cur[0];
        //     int cost = cur[1];
        //
        //     if (visited.containsKey(from)) {
        //         continue;
        //     }
        //     if (edges.containsKey(from)) {
        //         ArrayList<int[]> to = edges.get(from);
        //         for (int[] next : to) {
        //             if (!visited.containsKey(next[0])) {
        //                 priorityQueue.offer(new int[]{next[0], next[1] + cost});
        //             }
        //         }
        //     }
        //     visited.put(from, cost);
        //     ans = Math.max(ans, cost);
        // }
        //
        // if (visited.size() < n) {
        //     return -1;
        // }
        // return ans;

        // Bellman-Ford
        int[] dist = new int[n + 1];
        Arrays.fill(dist, (int)1e9);
        dist[k] = 0;

        for (int i = 1; i < n; i++) {
            boolean flag = false;
            for (int[] edge : times) {
                int from = edge[0];
                int to = edge[1];
                int cost = edge[2];
                if (dist[from] + cost < dist[to]) {
                    dist[to] = dist[from] + cost;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        int ans = 0;
        for (int i = 1; i < dist.length; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == (int) 1e9 ? -1 : ans;
    }
}
