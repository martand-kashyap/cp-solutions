package famousproblems;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class RottingOranges {
    /*
    You are given an m x n grid where each cell can have one of three values:
        0 representing an empty cell,
        1 representing a fresh orange, or
        2 representing a rotten orange.
    Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
    Return the minimum number of minutes that must elapse until no cell has a fresh orange.
    If this is impossible, return -1.

    Example:
        Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
        Output: 4
     */
    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        StringBuilder gridRep = new StringBuilder();
        for (int[] r : grid) {
            gridRep.append(Arrays.toString(r)).append("\n");
        }

        RottingOranges problem = new RottingOranges();

        String result =
                "Time taken to rot all oranges in the grid : " + "\n" +
                        gridRep + "\n" +
                        "BFS T(n) = O(m*n) S(n) = O(m*n) : " + problem.findTimeToRotUsingBFS(grid);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(result);
        pw.close();
    }

    private int findTimeToRotUsingBFS(int[][] grid) {
        int time = -1, freshOranges = 0, totalOranges = 0, m = grid.length, n = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> bfsQueue = new LinkedList<>();

        for (int i = 0; i < m; i += 1) {
            for (int j = 0; j < n; j += 1) {
                if (grid[i][j] == 2) {
                    bfsQueue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges += 1;
                }
            }
        }

        if (bfsQueue.size() == 0) {
            return -1;
        }
        totalOranges = freshOranges + bfsQueue.size();
        if (freshOranges == 0 || totalOranges == 0) {
            return 0;
        }

        while (!bfsQueue.isEmpty()) {
            int levelSize = bfsQueue.size();
            time += 1;

            for (int i = 0; i < levelSize; i++) {
                int x = bfsQueue.peek()[0], y = bfsQueue.peek()[1];
                bfsQueue.poll();

                for (int d = 0; d < 4; d++) {
                    int nX = x + directions[d][0], nY = y + directions[d][1];

                    if (isRangeValid(m, n, nX, nY) && grid[nX][nY] == 1) {
                        grid[nX][nY] = 2;
                        freshOranges -= 1;
                        bfsQueue.offer(new int[]{nX, nY});
                    }
                }
            }
        }

        return freshOranges > 0 ? -1 : time;
    }

    private boolean isRangeValid(int m, int n, int i, int j) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }
}
