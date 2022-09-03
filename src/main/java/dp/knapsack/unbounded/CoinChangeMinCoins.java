package dp.knapsack.unbounded;

import java.io.PrintWriter;
import java.util.Arrays;

class CoinChangeMinCoins {
    private int countMinWaysR(int[] denominations, int n, int totalAmount) {
        return solveR(denominations, n, totalAmount);
    }

    private int solveR(int[] denominations, int n, int totalAmount) {
        if (totalAmount == 0) {
            return 0;
        }

        if (n <= 0) {
            return Integer.MAX_VALUE;
        }

        int countByTakeCurrent = Integer.MAX_VALUE;
        for (int i = 0; i < n; i += 1) {
            if (denominations[i] <= totalAmount) {
                int takeCurrent = solveR(denominations, n, totalAmount - denominations[i]);
                if (takeCurrent <= Integer.MAX_VALUE) {
                    countByTakeCurrent = takeCurrent + 1;
                }
            }
        }

        int countBySkipCurrent = solveR(denominations, n - 1, totalAmount);

        return Integer.min(countByTakeCurrent, countBySkipCurrent);
    }

    private int countMinWaysM(int[] denominations, int n, int totalAmount) {
        int[][] dp = new int[n + 1][totalAmount + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return solveM(denominations, n, totalAmount, dp);
    }

    private int solveM(int[] denominations, int n, int totalAmount, int[][] dp) {
        if (totalAmount == 0) {
            return 0;
        }

        if (n <= 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[n][totalAmount] != Integer.MAX_VALUE) {
            return dp[n][totalAmount];
        }

        int countByTakeCurrent = Integer.MAX_VALUE;
        for (int i = 0; i < n; i += 1) {
            if (denominations[i] <= totalAmount) {
                int takeCurrent = solveM(denominations, n, totalAmount - denominations[i], dp);
                if (takeCurrent <= Integer.MAX_VALUE) {
                    countByTakeCurrent = takeCurrent + 1;
                }
            }
        }

        int countBySkipCurrent = solveM(denominations, n - 1, totalAmount, dp);

        return dp[n][totalAmount] = Integer.min(countByTakeCurrent, countBySkipCurrent);
    }

    private int countMinWaysT(int[] denominations, int n, int totalAmount) {
        /*int[][] dp = new int[n + 1][totalAmount + 1];
        for (int coinIdx = 0; coinIdx < n + 1; coinIdx += 1) {
            for (int amt = 0; amt < totalAmount + 1; amt += 1) {
                if (amt == 0) {
                    dp[coinIdx][amt] = 0;
                }
                if (coinIdx == 0) {
                    dp[coinIdx][amt] = Integer.MAX_VALUE;
                }
            }
        }

        for (int coinIdx = 0; coinIdx < n; coinIdx += 1) {
            for (int amt = 1; amt <= totalAmount; amt += 1) {
                if (coinIdx > 0) {
                    dp[coinIdx][amt] = dp[coinIdx - 1][amt];
                }

                if (denominations[coinIdx] <= amt) {
                    int takeCurrent = dp[coinIdx][amt - denominations[coinIdx]];
                    if (takeCurrent != Integer.MAX_VALUE) {
                        int countByTakeCurrent = takeCurrent + 1;
                        dp[coinIdx][amt] = Integer.min(countByTakeCurrent, dp[coinIdx][amt]);
                    }
                }
            }
        }

        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }

        return dp[n - 1][totalAmount] == Integer.MAX_VALUE ? -1 : dp[n - 1][totalAmount];*/
        return -1;
    }

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        int n = denominations.length;
        int totalAmount = 5;

        CoinChangeMinCoins minWays = new CoinChangeMinCoins();

        String sb =
                "Recursive : " + minWays.countMinWaysR(denominations, n, totalAmount) + "\n" +
//                        "Recursive (Optimized) : " + minWays.countMinWaysR(denominations, n, totalAmount) + "\n" +
                        "Memoized (Top-Down) : " + minWays.countMinWaysM(denominations, n, totalAmount) + "\n" +
                        "Tabulation 2D (Bottom-Up) : " + minWays.countMinWaysT(denominations, n, totalAmount) + "\n" +
                        "Tabulation 1D (Bottom-Up) : " + minWays.countMinWaysR(denominations, n, totalAmount);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);
        pw.close();
    }
}
