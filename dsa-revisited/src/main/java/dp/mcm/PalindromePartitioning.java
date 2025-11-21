package dp.mcm;

import java.io.PrintWriter;
import java.util.Arrays;

class PalindromePartitioning {
    public static void main(String[] args) {
        String input = "nitish";

        PalindromePartitioning pp = new PalindromePartitioning();
        String res = "Recursive T(n) = O(n.2^n)) : " + pp.findMinimumCutsR(input) + "\n" +
                "Memoized (Top-Down) T(n) = O(n.n^2) : " + pp.findMinimumCutsM(input);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int findMinimumCutsR(String input) {
        return solveR(input.toCharArray(), 0, input.length() - 1);
    }

    private int solveR(char[] input, int i, int j) {
        if (i >= j || isPalindrome(input, i, j)) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k += 1) {
            int t1 = solveR(input, i, k);
            int t2 = solveR(input, k + 1, j);
            int cost = t1 + t2 + 1;

            minCost = Integer.min(minCost, cost);
        }

        return minCost;
    }

    private int findMinimumCutsM(String input) {
        int n = input.length();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveM(input.toCharArray(), 0, n - 1, dp);
    }

    private int solveM(char[] input, int i, int j, int[][] dp) {
        if (i >= j || isPalindrome(input, i, j)) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k += 1) {
            int t1 = dp[i][k] != -1 ? dp[i][k] : solveM(input, i, k, dp);
            int t2 = dp[k + 1][j] != -1 ? dp[k + 1][j] : solveM(input, k + 1, j, dp);
            int cost = t1 + t2 + 1;

            minCost = Integer.min(minCost, cost);
        }

        return dp[i][j] = minCost;
    }

    private boolean isPalindrome(char[] arr, int i, int j) {
        if (i >= j) {
            return true;
        }

        for (; i < j; i += 1, j -= 1) {
            if (arr[i] != arr[j]) {
                return false;
            }
        }

        return true;
    }

    /*private boolean isPalindromeR(char[] input, int i, int j) {
        if (i > j || input[i] != input[j]) {
            return false;
        }

        if (i == j) {
            return true;
        }

        if (i < j + 1) {
            return isPalindromeR(input, i + 1, j - 1);
        }
        return false;
    }*/
}
