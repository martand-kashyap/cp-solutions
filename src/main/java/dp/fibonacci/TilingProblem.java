package dp.fibonacci;

import java.io.PrintWriter;
import java.util.Arrays;

class TilingProblem {
    /*-
        Given a “2 x n” board and tiles of size “2 x 1”,
        count the number of ways to tile the given board using the 2 x 1 tiles.
        A tile can either be placed horizontally i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.

        Input: n = 4
        Output: 5
        Explanation:
            For a 2 x 4 board, there are 5 ways
                All 4 vertical (1 way)
                All 4 horizontal (1 way)
                2 vertical and 2 horizontal (3 ways)

        IMP: - This is nothing but Fibonacci Sequence
     */
    public static void main(String[] args) {
        int n = 4;
        TilingProblem tp = new TilingProblem();

        String sb =
                "Recursive : " + tp.getNumberOfWaysR(n) + "\n" +
                        "Memoized (Top-Down) : " + tp.getNumberOfWaysM(n) + "\n" +
                        "Tabulation (Bottom-Up) : " + tp.getNumberOfWaysT(n) + "\n" +
                        "Optimized : " + tp.getNumberOfWaysO(n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);
        pw.close();
    }

    private int getNumberOfWaysR(int n) {
        if (n <= 2) {
            return n;
        }

        return getNumberOfWaysR(n - 1) + getNumberOfWaysR(n - 2);
    }

    private int getNumberOfWaysM(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return solveM(n, dp);
    }

    private int solveM(int n, int[] dp) {
        if (n <= 2) {
            return n;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        return dp[n] = getNumberOfWaysR(n - 1) + getNumberOfWaysR(n - 2);
    }

    private int getNumberOfWaysT(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i += 1) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    private int getNumberOfWaysO(int n) {
        if (n <= 2) {
            return n;
        }

        int b = 1, c = 2, d = -1;

        for (int i = 3; i <= n; i++) {
            d = b + c;
            b = c;
            c = d;
        }

        return d;
    }
}
