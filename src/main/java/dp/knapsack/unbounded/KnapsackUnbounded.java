package dp.knapsack.unbounded;

import java.io.PrintWriter;
import java.util.Arrays;

class KnapsackUnbounded {
    private int maximizeProfitR(int[] weights, int[] profits, int n, int knapsackCapacity) {
        return solveR(weights, profits, n, knapsackCapacity);
    }

    private int solveR(int[] weights, int[] profits, int n, int knapsackCapacity) {
        if (knapsackCapacity == 0 || n == 0)
            return 0;

        if (weights[n - 1] <= knapsackCapacity)
            return Integer.max(
                    profits[n - 1] + solveR(weights, profits, n, knapsackCapacity - weights[n - 1]),
                    solveR(weights, profits, n - 1, knapsackCapacity)
            );
        else
            return solveR(weights, profits, n - 1, knapsackCapacity);
    }

    private int maximizeProfitM(int[] weights, int[] profits, int n, int knapsackCapacity) {
        int[][] dp = new int[n + 1][knapsackCapacity + 1];
        for (int[] r : dp)
            Arrays.fill(r, -1);

        return solveM(weights, profits, n, knapsackCapacity, dp);
    }

    private int solveM(int[] weights, int[] profits, int n, int knapsackCapacity, int[][] dp) {
        if (knapsackCapacity == 0 || n == 0)
            return 0;

        if (dp[n][knapsackCapacity] != -1)
            return dp[n][knapsackCapacity];

        if (weights[n - 1] <= knapsackCapacity)
            return dp[n][knapsackCapacity] =
                    Integer.max(
                            profits[n - 1] + solveM(weights, profits, n, knapsackCapacity - weights[n - 1], dp),
                            solveM(weights, profits, n - 1, knapsackCapacity, dp)
                    );
        else
            return dp[n][knapsackCapacity] =
                    solveM(weights, profits, n - 1, knapsackCapacity, dp);
    }

    private int maximizeProfitT(int[] weights, int[] profits, int n, int knapsackCapacity) {
        int[][] dp = new int[n + 1][knapsackCapacity + 1];

        for (int i = 0; i <= n; i += 1)
            for (int w = 0; w <= knapsackCapacity; w += 1)
                if (i == 0 || w == 0)
                    dp[i][w] = 0;

        for (int i = 1; i <= n; i += 1) {
            for (int w = 1; w <= knapsackCapacity; w += 1) {
                if (weights[i - 1] <= w)
                    dp[i][w] = Integer.max(
                            profits[i - 1] + dp[i][w - weights[i - 1]],
                            dp[i - 1][w]
                    );
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][knapsackCapacity];
    }

    private int maximizeProfitT1D(int[] weights, int[] profits, int n, int knapsackCapacity) {
        int[] dp = new int[knapsackCapacity + 1];

        for (int i = 1; i <= n; i += 1) {
            for (int w = 0; w <= knapsackCapacity; w += 1) {
                if (weights[i - 1] <= w)
                    dp[w] = Integer.max(
                            profits[i - 1] + dp[w - weights[i - 1]],
                            dp[w]
                    );
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[knapsackCapacity];
    }

    public static void main(String[] args) {
        int knapsackCapacity = 5;
        int[] profits = { 15, 20, 50 };
        int[] weights = { 1, 2, 3 };
        int n = weights.length;

        KnapsackUnbounded kUn = new KnapsackUnbounded();

        String sb = "Recursive : " + kUn.maximizeProfitR(weights, profits, n, knapsackCapacity) + "\n"
                + "Memoized (Top-Down) : " + kUn.maximizeProfitM(weights, profits, n, knapsackCapacity) + "\n"
                + "Tabulation (Bottom-Up) [2D] : " + kUn.maximizeProfitT(weights, profits, n, knapsackCapacity) + "\n"
                + "Tabulation (Bottom-Up) [1D]: " + kUn.maximizeProfitT1D(weights, profits, n, knapsackCapacity);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
