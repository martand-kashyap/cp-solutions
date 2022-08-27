package dp.knapsack.zeroone;

import java.io.PrintWriter;
import java.util.Arrays;

class Knapsack01{
    private int maximizeProfitR(int[] weights, int[] profits, int n, int knapsackCapacity) {
        return solveR(weights, profits, n, knapsackCapacity);
    }

    private int solveR(int[] weights, int[] profits, int n, int knapsackCapacity) {
        if (n == 0 || knapsackCapacity == 0)
            return 0;
        if (weights[n - 1] <= knapsackCapacity)
            return Math.max(profits[n - 1] + solveR(weights, profits, n - 1, knapsackCapacity - weights[n - 1]),
                    solveR(weights, profits, n - 1, knapsackCapacity));
        else
            return solveR(weights, profits, n - 1, knapsackCapacity);
    }

    private int maximizeProfitM(int[] weights, int[] profits, int n, int knapsackCapacity) {
        int[][] dp = new int[n + 1][knapsackCapacity + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveM(weights, profits, n, knapsackCapacity, dp);
    }

    private int solveM(int[] weights, int[] profits, int n, int knapsackCapacity, int[][] dp) {
        if (n == 0 || knapsackCapacity == 0)
            return 0;

        if (dp[n - 1][knapsackCapacity] != -1)
            return dp[n - 1][knapsackCapacity];

        if (weights[n - 1] <= knapsackCapacity)
            return dp[n - 1][knapsackCapacity] = Math.max(
                    profits[n - 1] + solveM(weights, profits, n - 1, knapsackCapacity - weights[n - 1], dp),
                    solveM(weights, profits, n - 1, knapsackCapacity, dp));
        else
            return dp[n - 1][knapsackCapacity] = solveM(weights, profits, n - 1, knapsackCapacity, dp);
    }

    private int maximizeProfitT(int[] weights, int[] profits, int n, int knapsackCapacity) {
        int[][] dp = new int[n + 1][knapsackCapacity + 1];

        for (int item = 0; item < n + 1; item++)
            for (int wt = 0; wt < knapsackCapacity + 1; wt++)
                if (item == 0 || wt == 0)
                    dp[item][wt] = 0;

        for (int item = 1; item < n + 1; item++) {
            for (int wt = 1; wt < knapsackCapacity + 1; wt++) {
                if (weights[item - 1] <= wt)
                    dp[item][wt] = Math.max(profits[item - 1] + dp[item - 1][wt - weights[item - 1]], dp[item - 1][wt]);
                else
                    dp[item][wt] = dp[item - 1][wt];
            }
        }

        return dp[n][knapsackCapacity];
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 1, 4};
        int[] profits = {4, 5, 3, 7};
        int knapsackCapacity = 5;
        int n = weights.length;

        Knapsack01 k01 = new Knapsack01();

        String sb = "Recursive : " + k01.maximizeProfitR(weights, profits, n, knapsackCapacity) + "\n"
                + "Memoized (Top-Down) : " + k01.maximizeProfitM(weights, profits, n, knapsackCapacity) + "\n"
                + "Tabulation (Bottom-Up) : " + k01.maximizeProfitT(weights, profits, n, knapsackCapacity);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }

}
