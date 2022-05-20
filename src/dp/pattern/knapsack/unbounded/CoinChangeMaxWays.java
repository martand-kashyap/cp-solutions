package dp.pattern.knapsack.unbounded;

import java.io.PrintWriter;

class CoinChangeMaxWays {
    private int countMaxWaysR(int[] denominations, int n, int totalAmount) {
        return solveR(denominations, n, totalAmount);
    }

    private int solveR(int[] denominations, int n, int totalAmount) {
        if (totalAmount == 0) return 1;
        if (n == 0) return 0;

        if (denominations[n - 1] <= totalAmount) {
            return solveR(denominations, n, totalAmount - denominations[n - 1]) +
                   solveR(denominations, n - 1, totalAmount);
        } else
            return solveR(denominations, n - 1, totalAmount);
    }

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        int n = denominations.length;
        int totalAmount = 5;

        CoinChangeMaxWays maxWays = new CoinChangeMaxWays();

        String sb =
                "Recursive : " + maxWays.countMaxWaysR(denominations, n, totalAmount) + "\n" +
                        "Memoized (Top-Down) : " + maxWays.countMaxWaysR(denominations, n, totalAmount) + "\n" +
                        "Tabulation (Bottom-Up) : " + maxWays.countMaxWaysR(denominations, n, totalAmount);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }


}
