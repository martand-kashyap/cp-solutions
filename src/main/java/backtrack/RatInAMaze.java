package backtrack;

import java.util.ArrayList;
import java.util.List;

class RatInAMaze {
    static List<String> possiblePaths = new ArrayList<>();
    static final int MAX = 5;
    static int[][] possibleMoves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static char[] directions = {'R', 'L', 'D', 'U'};

    static boolean isSafe(int row, int col, int[][] m, int n, boolean[][] visited) {
        return row != -1 && row != n && col != -1 && col != n && !visited[row][col] && m[row][col] != 0;
    }

    static void printPathUtil(int row, int col, int[][] m, int n, boolean[][] visited, String path) {
        // This will check the initial point
        // (i.e. (0, 0)) to start the paths.
        if (row == -1 || row == n || col == -1 || col == n || visited[row][col] || m[row][col] == 0)
            return;

        // If reach the last cell (n-1, n-1)
        // then store the path and return
        if (row == n - 1 && col == n - 1 && m[row][col] == 1) {
            possiblePaths.add(path);
            return;
        }

        for (int d = 0; d < possibleMoves.length; d++) {
            int nr = row + possibleMoves[d][0];
            int nc = col + possibleMoves[d][1];

            // Mark the cell as visited
            visited[row][col] = true;

            // Check if downward move is valid
            if (isSafe(nr, nc, m, n, visited)) {
                path += directions[d];
                printPathUtil(nr, nc, m, n, visited, path);
                path = path.substring(0, path.length() - 1);
            }

            //backtrack
            visited[row][col] = false;
        }
    }

    static void printPath(int[][] m, int n) {
        boolean[][] visited = new boolean[n][MAX];

        // Call the utility function to
        // find the valid paths
        printPathUtil(0, 0, m, n, visited, "");

        possiblePaths.forEach(System.out::println);
    }

    public static void main(String[] args) {
        int[][] m = {
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 1, 1}};
        int n = m.length;
        printPath(m, n);
    }
}