package dp.pattern.lcs;

import java.io.PrintWriter;
import java.util.Arrays;

class LongestCommonSubtring {
    private int maxLCSLengthR(String x, String y) {
        char[] a = x.toLowerCase().toCharArray();
        char[] b = y.toLowerCase().toCharArray();
        int m = a.length, n = b.length, count = 0;

        return solveR(a, b, m, n, count);
    }

    private int solveR(char[] a, char[] b, int m, int n, int count) {
        if (m == 0 || n == 0)
            return count;

        if (a[m - 1] == b[n - 1])
            count = solveR(a, b, m - 1, n - 1, count + 1);

        return Integer.max(
                count,
                Integer.max(
                        solveR(a, b, m - 1, n, 0),
                        solveR(a, b, m, n - 1, 0)
                )
        );
    }

    private int maxLCSLengthM(String x, String y) {
        char[] a = x.toLowerCase().toCharArray();
        char[] b = y.toLowerCase().toCharArray();
        int m = a.length, n = b.length, count = 0;

        int[][] dp = new int[m + 1][n + 1];
        for (int[] r : dp)
            Arrays.fill(r, -1);

        return solveM(a, b, m, n, dp, count);
    }

    private int solveM(char[] a, char[] b, int m, int n, int[][] dp, int count) {
        if (m == 0 || n == 0)
            return count;

        if (dp[m][n] != -1)
            return dp[m][n];

        if (a[m - 1] == b[n - 1])
            count = solveM(a, b, m - 1, n - 1, dp, count + 1);

        return dp[m][n] =
                Integer.max(
                        count,
                        Integer.max(
                                solveM(a, b, m - 1, n, dp, 0),
                                solveM(a, b, m, n - 1, dp, 0)
                        )
                );
    }

    private int maxLCSLengthT(String x, String y) {
        char[] a = x.toLowerCase().toCharArray();
        char[] b = y.toLowerCase().toCharArray();
        int m = a.length, n = b.length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i += 1)
            for (int j = 0; j <= n; j += 1)
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

        int max = 0;
        for (int i = 1; i <= m; i += 1) {
            for (int j = 1; j <= n; j += 1) {
                if (a[i - 1] == b[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 0;

                max = Integer.max(max, dp[i][j]);
            }
        }

        return max;
    }

    private String printLCS(String x, String y) {
        char[] a = x.toLowerCase().toCharArray();
        char[] b = y.toLowerCase().toCharArray();
        int m = a.length, n = b.length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i += 1)
            for (int j = 0; j <= n; j += 1)
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

        int max = 0, r_i = 0, r_j = 0;
        for (int i = 1; i <= m; i += 1) {
            for (int j = 1; j <= n; j += 1) {
                if (a[i - 1] == b[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 0;

                if (max < dp[i][j]) {
                    max = Integer.max(max, dp[i][j]);
                    r_i = i;
                    r_j = j;
                }
            }
        }

        char[] lcsString = new char[max];
        int i = r_i, j = r_j, k = max;
        while (k>0) {
            if (a[i - 1] == b[j - 1]) {
                lcsString[k - 1] = a[i - 1];
                k -= 1;
                i -= 1;
                j -= 1;
            }
        }
        return String.valueOf(lcsString).toUpperCase();
    }

    public static void main(String[] args) {
        String X = "abdca", Y = "cbda"; // LCS is GTAB
        LongestCommonSubtring lcs = new LongestCommonSubtring();

        String sb =
                "Recursive : " + lcs.maxLCSLengthR(X, Y) + "\n" +
                "Memoized (Top-Down) : " + lcs.maxLCSLengthM(X, Y) + "\n" +
                "Tabulation (Bottom-Up) : " + lcs.maxLCSLengthT(X, Y) + "\n" +
                "Longest Common Substring : " + lcs.printLCS(X, Y);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
