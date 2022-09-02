package dp.knapsack.unbounded;

import java.io.PrintWriter;
import java.util.Arrays;

class CoinChangeMaxWays {
    private int countMaxWaysR(int[] denominations, int n, int totalAmount) {
        return solveR(denominations, n, totalAmount);
    }

    private int solveR(int[] denominations, int n, int totalAmount) {
        if (totalAmount == 0) {
            return 1;
        }

        if (n == 0) {
            return 0;
        }

        if (denominations[n - 1] <= totalAmount) {
            return solveR(denominations, n, totalAmount - denominations[n - 1]) +
                    solveR(denominations, n - 1, totalAmount);
        } else
            return solveR(denominations, n - 1, totalAmount);
    }

    private int countMaxWaysROptimized(int[] denominations, int n, int totalAmount) {
        return solveROptimized(denominations, n, totalAmount);
    }

    private int solveROptimized(int[] denominations, int n, int totalAmount) {
        if (totalAmount == 0) {
            return 1;
        }

        if (totalAmount <= 0) {
            return 0;
        }

        if (n == 0) {
            return 0;
        }

        return solveROptimized(denominations, n, totalAmount - denominations[n - 1]) +
                solveROptimized(denominations, n - 1, totalAmount);
    }

    private int countMaxWaysM(int[] denominations, int n, int totalAmount) {
        int[][] dp = new int[n + 1][totalAmount + 1];
        for (int[] r : dp) {
            Arrays.fill(r, -1);
        }

        return solveM(denominations, n, totalAmount, dp);
    }

    private int solveM(int[] denominations, int n, int totalAmount, int[][] dp) {
        if (totalAmount == 0) {
            return 1;
        }

        if (n == 0) {
            return 0;
        }

        if (dp[n][totalAmount] != -1) {
            return dp[n][totalAmount];
        }

        if (denominations[n - 1] <= totalAmount) {
            return dp[n][totalAmount] =
                    solveM(denominations, n, totalAmount - denominations[n - 1], dp) +
                            solveM(denominations, n - 1, totalAmount, dp);
        } else
            return dp[n][totalAmount] = solveM(denominations, n - 1, totalAmount, dp);
    }

    private int countMaxWaysT(int[] denominations, int n, int totalAmount) {
        int[][] dp = new int[n + 1][totalAmount + 1];

        for (int amt = 0; amt <= totalAmount; amt += 1) {
            dp[0][amt] = 0;
        }
        for (int coin = 0; coin <= n; coin += 1) {
            dp[coin][0] = 1;
        }

        for (int coin = 1; coin <= n; coin += 1) {
            for (int amt = 1; amt <= totalAmount; amt += 1) {
                if (denominations[coin - 1] <= amt) {
                    dp[coin][amt] =
                            dp[coin][amt - denominations[coin - 1]] +
                                    dp[coin - 1][amt];
                } else
                    dp[coin][amt] = dp[coin - 1][amt];
            }
        }

        return dp[n][totalAmount];
    }

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        int n = denominations.length;
        int totalAmount = 5;

        CoinChangeMaxWays maxWays = new CoinChangeMaxWays();

        String sb =
                "Recursive : " + maxWays.countMaxWaysR(denominations, n, totalAmount) + "\n" +
                        "Recursive (Optimized) : " + maxWays.countMaxWaysROptimized(denominations, n, totalAmount) + "\n" +
                        "Memoized (Top-Down) : " + maxWays.countMaxWaysM(denominations, n, totalAmount) + "\n" +
                        "Tabulation (Bottom-Up) : " + maxWays.countMaxWaysT(denominations, n, totalAmount);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);
        pw.close();
    }
}
