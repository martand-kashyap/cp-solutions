package dp.pattern.knapsack.zeroone;

import java.io.PrintWriter;
import java.util.Arrays;

class SubsetSum {
    private boolean subsetExistsWithSumR(int[] items, int targetSum, int n) {
        return solveR(items, targetSum, n);
    }

    private boolean solveR(int[] items, int targetSum, int n) {
        if (n == 0)
            return false;

        if (targetSum == 0)
            return true;

        if (items[n - 1] <= targetSum)
            return solveR(items, targetSum - items[n - 1], n - 1) // choice 1 : take the current item
                    || solveR(items, targetSum, n - 1); // choice 2 : do not take the current item
        else
            return solveR(items, targetSum, n - 1);
    }

    private boolean subsetExistsWithSumM(int[] items, int targetSum, int n) {
        boolean[][] dp = new boolean[n + 1][targetSum + 1];
        for (boolean[] row : dp)
            Arrays.fill(row, false);

        return solveM(items, targetSum, n, dp);
    }

    private boolean solveM(int[] items, int targetSum, int n, boolean[][] dp) {
        if (n == 0)
            return false;

        if (targetSum == 0)
            return true;

        if (dp[n][targetSum])
            return dp[n][targetSum];

        if (items[n - 1] <= targetSum)
            return dp[n][targetSum] =
                    solveM(items, targetSum - items[n - 1], n - 1, dp) // choice 1 : take the current item
                            || solveM(items, targetSum, n - 1, dp); // choice 2 : do not take the current item
        else
            return dp[n][targetSum] = solveM(items, targetSum, n - 1, dp);
    }

    private boolean subsetExistsWithSumT(int[] items, int targetSum, int n) {
        boolean[][] dp = new boolean[n + 1][targetSum + 1];

        for (int item = 0; item <= n; item++)
            dp[item][0] = true;
        for (int sum = 0; sum <= targetSum; sum++)
            dp[0][sum] = false;

        for (int item = 1; item <= n; item++) {
            for (int sum = 1; sum <= targetSum; sum++) {
                if (items[item - 1] <= sum)
                    dp[item][sum] = dp[item - 1][sum - items[item - 1]] || dp[item - 1][sum];
                else
                    dp[item][sum] = dp[item - 1][sum];
            }
        }

        return dp[n][targetSum];
    }

    public static void main(String[] args) {
        int[] items = {2, 3, 1, 4};
        int targetSum = 5;
        int n = items.length;

        SubsetSum sSum = new SubsetSum();

        String sb = "Recursive : " + sSum.subsetExistsWithSumR(items, targetSum, n) + "\n"
                + "Memoized (Top-Down) : " + sSum.subsetExistsWithSumM(items, targetSum, n) + "\n"
                + "Tabulation (Bottom-Up) : " + sSum.subsetExistsWithSumT(items, targetSum, n);


        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}