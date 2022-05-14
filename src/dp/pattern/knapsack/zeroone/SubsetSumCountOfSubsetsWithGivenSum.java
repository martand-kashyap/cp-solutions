package dp.pattern.knapsack.zeroone;

import java.io.PrintWriter;
import java.util.Arrays;

class SubsetSumCountOfSubsetsWithGivenSum {
    int countSubsetsWithSumR(int[] items, int targetSum, int n) {
        return solveR(items, targetSum, n);
    }

    private int solveR(int[] items, int targetSum, int n) {
        if (n == 0) {
            if (targetSum == 0)
                return 1;
            else
                return 0;
        }

        if (items[n - 1] > targetSum)
            return solveR(items, targetSum, n - 1);
        else
            return solveR(items, targetSum - items[n - 1], n - 1) // choice 1 : take the current item
                    + solveR(items, targetSum, n - 1); // choice 2 : do not take the current item
    }

    int countSubsetsWithSumM(int[] items, int targetSum, int n) {
        int[][] dp = new int[n + 1][targetSum + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveM(items, targetSum, n, dp);
    }

    private int solveM(int[] items, int targetSum, int n, int[][] dp) {
        if (n == 0) {
            if (targetSum == 0)
                return 1;
            else
                return 0;
        }

        if (dp[n][targetSum] != -1)
            return dp[n][targetSum];

        if (items[n - 1] > targetSum)
            return dp[n][targetSum] =
                    solveM(items, targetSum, n - 1, dp);
        else
            return dp[n][targetSum] =
                    solveM(items, targetSum - items[n - 1], n - 1, dp) // choice 1 : take the current item
                            + solveM(items, targetSum, n - 1, dp); // choice 2 : do not take the current item
    }

    int countSubsetsWithSumT(int[] items, int targetSum, int n) {
        int[][] dp = new int[n + 1][targetSum + 1];

        for (int item = 0; item <= n; item++)
            dp[item][0] = 1;
        for (int sum = 1; sum <= targetSum; sum++)
            dp[0][sum] = 0;
        dp[0][0] = 1;

        for (int item = 1; item <= n; item++) {
            for (int sum = 1; sum <= targetSum; sum++) {
                if (items[item - 1] <= sum)
                    dp[item][sum] = dp[item - 1][sum - items[item - 1]] + dp[item - 1][sum];
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

        SubsetSumCountOfSubsetsWithGivenSum subsetSumCount = new SubsetSumCountOfSubsetsWithGivenSum();

        String sb = "Recursive : " + subsetSumCount.countSubsetsWithSumR(items, targetSum, n) + "\n"
                + "Memoized (Top-Down) : " + subsetSumCount.countSubsetsWithSumM(items, targetSum, n) + "\n"
                + "Tabulation (Bottom-Up) : " + subsetSumCount.countSubsetsWithSumT(items, targetSum, n);


        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }

}
