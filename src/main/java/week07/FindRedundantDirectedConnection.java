package week07;

public class FindRedundantDirectedConnection {

    class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            parents = new int[n + 1];
            for (int i = 0; i < n; i++) {
                parents[i + 1] = i + 1;
            }
        }

        public void union(int i, int j) {
            parents[find(i)] = find(j);
        }

        public int find(int i) {
            if (parents[i] != i) {
                parents[i] = find(parents[i]);
            }
            return parents[i];
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length + 1);
        int[] parents = new int[edges.length + 1];
        for (int i = 1; i <= edges.length; i++) {
            parents[i] = i;
        }
        int problemNode = -1;
        int cycle = -1;
        for (int i = 0; i < edges.length; i++) {
            if (parents[edges[i][1]] != edges[i][1]) {
                problemNode = i;
            } else {
                parents[edges[i][1]] = edges[i][0];
                if (unionFind.find(edges[i][0]) == unionFind.find(edges[i][1])) {
                    cycle = i;
                } else {
                    unionFind.union(edges[i][0], edges[i][1]);
                }
            }
        }
        if (problemNode == -1) {
            return new int[] {edges[cycle][0], edges[cycle][1]};
        } else {
            if (cycle == -1) {
                return edges[problemNode];
            } else {
                return new int[] {parents[edges[problemNode][1]], edges[problemNode][1]};
            }
        }
    }
}
