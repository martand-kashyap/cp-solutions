package dp.pattern.knapsack.zeroone;

import java.io.PrintWriter;
import java.util.Arrays;

class KnapsackZeroOne {
    private int maximizeProfitR(int[] weights, int[] profits, int itemCount, int capacity) {
        return solveR(weights, profits, itemCount, capacity, itemCount - 1);
    }

    private int solveR(int[] weights, int[] profits, int itemCount, int capacity, int index) {
        if (capacity == 0 || weights.length == 0 || index == 0)
            return 0;

        if (capacity >= weights[index]) {
            return Integer.max(
                    profits[index] + solveR(weights, profits, itemCount, capacity - weights[index], index - 1),
                    solveR(weights, profits, itemCount, capacity, index - 1));
        } else
            return solveR(weights, profits, itemCount, capacity, index - 1);
    }

    private int maximizeProfitM(int[] weights, int[] profits, int itemCount, int capacity) {
        int[][] dp = new int[itemCount + 1][capacity + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveM(weights, profits, itemCount, capacity, dp, itemCount - 1);
    }

    private int solveM(int[] weights, int[] profits, int itemCount, int capacity, int[][] dp, int index) {
        if (capacity == 0 || weights.length == 0 || index == 0)
            return 0;

        if (capacity >= weights[index]) {
            return dp[index][capacity] = Integer.max(
                    profits[index] + solveM(weights, profits, itemCount, capacity - weights[index], dp, index - 1),
                    solveM(weights, profits, itemCount, capacity, dp, index - 1));
        } else
            return dp[index][capacity] = solveM(weights, profits, itemCount, capacity, dp, index - 1);
    }

    private int maximizeProfitT(int[] weights, int[] profits, int itemCount, int knapsackCapacity) {
        int[][] dp = new int[itemCount + 1][knapsackCapacity + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        //init the DP table
        for (int r = 0; r < itemCount + 1; r++)
            for (int c = 0; c < dp[r].length; c++)
                if (r == 0 || c == 0)
                    dp[r][c] = 0;

        for (int r = 1; r <= itemCount; r++) {
            for (int c = 1; c <= knapsackCapacity; c++) {
                if (c >= weights[r-1])
                    dp[r][c] = Math.max(profits[r-1] + dp[r-1][c - weights[r-1]], dp[r - 1][c]);
                else
                    dp[r][c] = dp[r - 1][c];
            }
        }

        return dp[itemCount][knapsackCapacity];
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 5};
        int[] profits = {1, 6, 10, 16};
        int knapsackCapacity = 7;
        KnapsackZeroOne k01 = new KnapsackZeroOne();

        String sb = "Recursive : " + k01.maximizeProfitR(weights, profits, weights.length, knapsackCapacity) + "\n" +
                "Memoized (Top-Down) : " + k01.maximizeProfitM(weights, profits, weights.length, knapsackCapacity) + "\n" +
                "Tabulation (Bottom-Up) : " + k01.maximizeProfitT(weights, profits, weights.length, knapsackCapacity);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }


}
