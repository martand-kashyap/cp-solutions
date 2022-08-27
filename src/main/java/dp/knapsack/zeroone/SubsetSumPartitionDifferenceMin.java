package dp.knapsack.zeroone;

import java.io.PrintWriter;
import java.util.Arrays;

class SubsetSumPartitionDifferenceMin {

    private int minDifferencePartitionR(int[] items, int n) {
        return solveR(items, n, 0, 0);
    }

    private int solveR(int[] items, int n, int subsetSum1, int subsetSum2) {
        if (n == 0)
            return Math.abs(subsetSum1 - subsetSum2);

        int diff1 = solveR(items, n - 1, subsetSum1 + items[n - 1], subsetSum2);
        int diff2 = solveR(items, n - 1, subsetSum1, subsetSum2 + items[n - 1]);

        return Integer.min(diff1, diff2);
    }

    private int minDifferencePartitionM(int[] items, int n) {
        int maxSum = Arrays.stream(items).sum();

        int[][] dp = new int[n + 1][maxSum + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveM(items, n, 0, 0, dp);
    }

    private int solveM(int[] items, int n, int subsetSum1, int subsetSum2, int[][] dp) {
        if (n == 0)
            return Math.abs(subsetSum1 - subsetSum2);

        if (dp[n - 1][subsetSum1] != -1)
            return dp[n - 1][subsetSum1];

        int diff1 = solveR(items, n - 1, subsetSum1 + items[n - 1], subsetSum2);
        int diff2 = solveR(items, n - 1, subsetSum1, subsetSum2 + items[n - 1]);

        return dp[n - 1][subsetSum1] = Integer.min(diff1, diff2);
    }

    private int minDifferencePartitionT(int[] items, int n) {
        int maxSum = Arrays.stream(items).sum();

		/*-
		   s1 + s2  = maxSum......(i)
		=> s2 = maxSum - s1
		1. Find a suitable candidate for s1
		2. Now we need to look for only one subset s2
		*/

        // subset sum
        boolean[][] dp = new boolean[n + 1][maxSum + 1];

        for (int sum = 0; sum <= maxSum; sum++)
            dp[0][sum] = false;
        for (int item = 0; item <= n; item++)
            dp[item][0] = true;

        for (int item = 1; item <= n; item++) {
            for (int sum = 1; sum <= maxSum; sum++) {
                if (items[item - 1] <= sum)
                    dp[item][sum] = dp[item - 1][sum - items[item - 1]] || dp[item - 1][sum];
                else
                    dp[item][sum] = dp[item - 1][sum];
            }
        }

		/*-
		s1 should exist in the range [0, maxSum/2]
		this ensures that s2 will exist in [maxSum/2, maxSum]
		
		We need to get the candidates for s1.
		*/
        int subset1Sum = 0, subset2Sum;
        for (int c = maxSum / 2; c >= 0; c--) {
            if (dp[n][c]) {
                subset1Sum = c;
                break;
            }
        }

        subset2Sum = maxSum - subset1Sum;

        return Math.abs(subset1Sum - subset2Sum);
    }

    public static void main(String[] args) {
        int[] items = {1, 2, 3, 9};
        int n = items.length;

        SubsetSumPartitionDifferenceMin minDifferencePartition = new SubsetSumPartitionDifferenceMin();

        String sb =
                "Minimum difference possible between two subsets \n" +
                "Recursive : " + minDifferencePartition.minDifferencePartitionR(items, n) + "\n" +
                "Memoized (Top-Down) : " + minDifferencePartition.minDifferencePartitionM(items, n) + "\n" +
                "Tabulation (Bottom-Up) : " + minDifferencePartition.minDifferencePartitionT(items, n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }

}
