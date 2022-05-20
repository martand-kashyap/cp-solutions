package dp.pattern.knapsack.unbounded;

import java.io.PrintWriter;
import java.util.Arrays;

class RodCutting {
    private int maximizeProfitR(int[] lengths, int[] prices, int n, int rodLength) {
        return solveR(lengths, prices, n, rodLength);
    }

    private int solveR(int[] lengths, int[] prices, int n, int rodLength) {
        if (rodLength == 0 || n == 0)
            return 0;

        if (lengths[n - 1] <= rodLength) {
            return Integer.max(
                    prices[n - 1] + solveR(lengths, prices, n, rodLength - lengths[n - 1]),
                    solveR(lengths, prices, n - 1, rodLength)
            );
        } else
            return solveR(lengths, prices, n - 1, rodLength);
    }

    private int maximizeProfitM(int[] lengths, int[] prices, int n, int rodLength) {
        int[][] dp = new int[n + 1][rodLength + 1];
        for (int[] r : dp)
            Arrays.fill(r, -1);

        return solveM(lengths, prices, n, rodLength, dp);
    }

    private int solveM(int[] lengths, int[] prices, int n, int rodLength, int[][] dp) {
        if (rodLength == 0 || n == 0)
            return 0;

        if (dp[n][rodLength] != -1)
            return dp[n][rodLength];

        if (lengths[n - 1] <= rodLength) {
            return dp[n][rodLength] =
                    Integer.max(
                            prices[n - 1] + solveM(lengths, prices, n, rodLength - lengths[n - 1], dp),
                            solveM(lengths, prices, n - 1, rodLength, dp)
                    );
        } else
            return dp[n][rodLength] =
                    solveM(lengths, prices, n - 1, rodLength, dp);
    }

    private int maximizeProfitT(int[] lengths, int[] prices, int n, int rodLength) {
        int[][] dp = new int[n + 1][rodLength + 1];

        for (int i = 0; i <= n; i += 1)
            for (int l = 0; l <= rodLength; l += 1)
                if (i == 0 || l == 0)
                    dp[i][l] = 0;

        for (int i = 1; i <= n; i += 1) {
            for (int l = 1; l <= rodLength; l += 1) {
                if (lengths[i - 1] <= l) {
                    dp[i][l] = Integer.max(
                            prices[i - 1] + dp[i][l - lengths[i - 1]],
                            dp[i - 1][l]
                    );
                } else
                    dp[i][l] = dp[i - 1][l];
            }
        }

        return dp[n][rodLength];
    }

    public static void main(String[] args) {
        int rodLength = 5;
        int[] prices = {2, 6, 7, 10, 13};
        int[] lengths = {1, 2, 3, 4, 5};
        int n = lengths.length;

        RodCutting rodCutting = new RodCutting();

        String sb =
                "Recursive : " + rodCutting.maximizeProfitR(lengths, prices, n, rodLength) + "\n" +
                "Memoized (Top-Down) : " + rodCutting.maximizeProfitM(lengths, prices, n, rodLength) + "\n" +
                "Tabulation (Bottom-Up) : " + rodCutting.maximizeProfitT(lengths, prices, n, rodLength);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
