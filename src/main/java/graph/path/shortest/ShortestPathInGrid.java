package graph.path.shortest;

import graph.XYPair;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class ShortestPathInGrid {
    /*-
    Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
    If there is no clear path, return -1.

    A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
        All the visited cells of the path are 0.
        All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
    The length of a clear path is the number of visited cells of this path.

    Input: grid = [[0,1],[1,0]]
    Output: 2

    Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
    Output: 4
     */
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        StringBuilder graphRep = new StringBuilder();
        for (int[] row : grid)
            graphRep.append(Arrays.toString(row)).append("\n");

        ShortestPathInGrid problem = new ShortestPathInGrid();

        String res =
                "A path from (0, 0) to (" + (grid.length - 1) + ", " + (grid[0].length - 1) + ") in the grid : " + "\n" +
                        graphRep + "\n" +
                        "DFS approach T(n) = O(V+E), S(n) = O(V) : " + problem.solveDFS(grid) + "\n" +
                        "BFS approach T(n) = O(V+E), S(n) = O(V) : " + problem.solveBFS(grid);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int solveDFS(int[][] grid) {
        return -1;
    }

    private int solveBFS(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        if (grid[0][0] != 0 || grid[m - 1][n - 1] != 0)
            return -1;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(visited[i], false);

        Queue<XYPair> queue = new LinkedList<>();
        queue.add(new XYPair(0, 0));
        visited[0][0] = true;
        int distance = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int l = 0; l < levelSize; l++) {
                int x = queue.peek().getX();
                int y = queue.peek().getY();

                if (x == m - 1 && y == n - 1) {
                    return distance;
                }

                for (int d = 0; d < 8; d++) {
                    int nx = x + directions[d][0];
                    int ny = y + directions[d][1];

                    if (0 <= nx && nx < m && 0 <= ny && ny < n && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new XYPair(nx, ny));
                    }
                }

                queue.poll();
            }
            distance++;
        }

        return -1;
    }
}
