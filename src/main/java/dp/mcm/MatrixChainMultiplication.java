package dp.mcm;

import java.io.PrintWriter;
import java.util.Arrays;

class MatrixChainMultiplication {
    public static void main(String[] args) {
        int[] sizes = {1, 2, 3, 4, 3};
        MatrixChainMultiplication mcm = new MatrixChainMultiplication();
        String res = "Using recursion: " + mcm.getOptimalMultiplicationCostR(sizes) + "\n"
                + "Memoized (Top-Down) : " + mcm.getOptimalMultiplicationCostM(sizes);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int getOptimalMultiplicationCostR(int[] sizes) {
        int n = sizes.length;
        return solveR(sizes, 1, n - 1);
    }

    private int solveR(int[] sizes, int i, int j) {
        if (i >= j) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k += 1) {
            int temp1 = solveR(sizes, i, k);
            int temp2 = solveR(sizes, k + 1, j);
            int cost = temp1 + temp2 + sizes[i - 1] * sizes[k] * sizes[j];

            minCost = Integer.min(minCost, cost);
        }

        return minCost;
    }

    private int getOptimalMultiplicationCostM(int[] sizes) {
        int n = sizes.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveM(sizes, 1, n - 1, dp);
    }

    private int solveM(int[] sizes, int i, int j, int[][] dp) {
        if (i >= j) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k += 1) {
            int temp1 = solveR(sizes, i, k);
            int temp2 = solveR(sizes, k + 1, j);
            int cost = temp1 + temp2 + sizes[i - 1] * sizes[k] * sizes[j];

            minCost = Integer.min(minCost, cost);
        }

        return dp[i][j] = minCost;
    }
}