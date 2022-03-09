package backtrack;

import java.util.Arrays;

public class NQueenAllSolutions {

    public static void main(String[] args) {
        int n = 4;

        boolean[][] visited = new boolean[n][n];
        for (boolean[] row : visited)
            Arrays.fill(row, false);

        int count = backtrack(visited, n, 0, 0);
        System.out.println(count);
    }

    private static int backtrack(boolean[][] visited, int n, int r, int count) {
        if (r == n)
            return 1;

        for (int c = 0; c < n; c++) {
            if(isSafe(visited, r, c, n)) {
                visited[r][c] = true;
                count+=backtrack(visited, n, r+1, count);
                visited[r][c]=false;
            }
        }

        return count;
    }

    private static boolean isSafe(boolean[][] visited, int r, int c, int n) {
        int i, j;
        for(i=0; i<r; i++)
            if(visited[i][c])
                return false;

        for(i=r, j=c; i>=0 && j>=0; i--, j--){
            if(visited[i][j])
                return false;

        for(i=r, j=c; i<n && j>=0; i++, j--)
            if(visited[i][j])
                return false;
        }
        return true;
    }
}