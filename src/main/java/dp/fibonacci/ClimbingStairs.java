package dp.fibonacci;

import java.io.PrintWriter;
import java.util.Arrays;


class ClimbingStairs {
    /*-
        You are climbing a staircase. It takes n steps to reach the top.
        Each time you can either climb 1 or 2 or 3 steps.
        In how many distinct ways can you climb to the top?
    */
    private int climbStairsR(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int part1 = climbStairsR(n - 1);
        int part2 = climbStairsR(n - 2);
        int part3 = climbStairsR(n - 3);

        return part1 + part2 + part3;
    }

    private int climbStairsM(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return memoize(n, dp);
    }

    private int memoize(int n, int[] dp) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        if (dp[n] != -1)
            return dp[n];

        int part1 = memoize(n - 1, dp);
        int part2 = memoize(n - 2, dp);
        int part3 = memoize(n - 3, dp);

        return part1 + part2 + part3;
    }

    private int climbStairsT(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];

        return dp[n];
    }

    private int optimized(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int a = 1, b = 1, c = 2, d = -1;
        for (int i = 3; i <= n; i++) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }

        return d;
    }

    public static void main(String[] args) {
        int n = 4;
        ClimbingStairs cs = new ClimbingStairs();

        String sb =
                "Recursive : " + cs.climbStairsR(n) + "\n" +
                        "Memoized (Top-Down) : " + cs.climbStairsM(n) + "\n" +
                        "Tabulation (Bottom-Up) : " + cs.climbStairsT(n) + "\n" +
                        "Optimized : " + cs.optimized(n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
