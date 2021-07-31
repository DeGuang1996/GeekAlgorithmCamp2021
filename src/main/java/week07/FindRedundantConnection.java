package week07;

public class FindRedundantConnection {

    private void union(int[] parents, int i, int j) {
        parents[find(parents, i)] = find(parents, j);
    }

    private int find(int[] parents, int i) {
        if (parents[i] != i) {
            parents[i] = find(parents, parents[i]);
        }
        return parents[i];
    }

    public int[] findRedundantConnection(int[][] edges) {
        int[] patents = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            patents[i + 1] = i + 1;
        }
        for (int i = 0; i < edges.length; i++) {
            if (find(patents, edges[i][0]) != find(patents, edges[i][1])) {
                union(patents, edges[i][0], edges[i][1]);
            } else {
                return edges[i];
            }
        }
        return null;
    }
}
