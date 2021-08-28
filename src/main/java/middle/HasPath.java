package middle;

import java.util.HashSet;

public class HasPath {

    private int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m;
    private int n;
    private HashSet<Integer> hashSet;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        hashSet = new HashSet<>();
        return dfs(maze, start, destination, new int[]{0, 0});
    }

    private boolean dfs(int[][] maze, int[] start, int[] destination, int[] from) {
        if (hashSet.contains(getIndex(start[0], start[1]))) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }
        for (int i = 0; i < dir.length; i++) {
            if (dir[i][0] + from[0] == 0 && dir[i][1] + from[1] == 0) {
                continue;
            }

            int[] nextStart = {start[0], start[1]};
            int[] tempStart = {start[0] + dir[i][0], start[1] + dir[i][1]};
            while (tempStart[0] >= 0 && tempStart[0] < m && tempStart[1] >= 0 && tempStart[1] < n && maze[tempStart[0]][tempStart[1]] == 0) {
                nextStart[0] = tempStart[0]; nextStart[1] = tempStart[1];
                tempStart[0] = tempStart[0] + dir[i][0];
                tempStart[1] = tempStart[1] + dir[i][1];
            }
            if (nextStart[0] != start[0] || nextStart[1] != start[1]) {
                hashSet.add(getIndex(start[0], start[1]));
                if (dfs(maze, nextStart, destination, dir[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getIndex(int i, int j) {
        return i * n + j;
    }

    public static void main(String[] args) {
        int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        HasPath hasPath = new HasPath();
        System.out.println(hasPath.hasPath(maze, new int[]{0,4}, new int[]{4,4}));
    }
}
