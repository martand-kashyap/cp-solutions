package dp.lcs;

import java.io.PrintWriter;
import java.util.Arrays;

public class LongestCommonSubsequence {
    private int maxLCSLengthR(String x, String y) {
        char[] a = x.toLowerCase().toCharArray();
        char[] b = y.toLowerCase().toCharArray();
        int m = a.length, n = b.length;

        return solveR(a, b, m, n);
    }

    private int solveR(char[] a, char[] b, int m, int n) {
        if (m == 0 || n == 0)
            return 0;

        if (a[m - 1] == b[n - 1])
            return 1 + solveR(a, b, m - 1, n - 1);
        else
            return Integer.max(
                    solveR(a, b, m - 1, n),
                    solveR(a, b, m, n - 1)
            );
    }

    private int maxLCSLengthM(String x, String y) {
        char[] a = x.toLowerCase().toCharArray();
        char[] b = y.toLowerCase().toCharArray();
        int m = a.length, n = b.length;

        int[][] dp = new int[m + 1][n + 1];
        for (int[] r : dp)
            Arrays.fill(r, -1);

        return solveM(a, b, m, n, dp);
    }

    private int solveM(char[] a, char[] b, int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return 0;

        if (dp[m][n] != -1)
            return dp[m][n];

        if (a[m - 1] == b[n - 1])
            return dp[m][n] =
                    1 + solveM(a, b, m - 1, n - 1, dp);
        else
            return dp[m][n] =
                    Integer.max(
                            solveM(a, b, m - 1, n, dp),
                            solveM(a, b, m, n - 1, dp)
                    );
    }

    public int maxLCSLengthT(String x, String y) {
        char[] a = x.toLowerCase().toCharArray();
        char[] b = y.toLowerCase().toCharArray();
        int m = a.length, n = b.length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i += 1)
            for (int j = 0; j <= n; j += 1)
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

        for (int i = 1; i <= m; i += 1) {
            for (int j = 1; j <= n; j += 1) {
                if (a[i - 1] == b[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Integer.max(
                            dp[i - 1][j],
                            dp[i][j - 1]
                    );
            }
        }

        return dp[m][n];
    }

    public String printLCS(String x, String y) {
        char[] a = x.toLowerCase().toCharArray();
        char[] b = y.toLowerCase().toCharArray();
        int m = a.length, n = b.length;

        //populate LCS table
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i += 1)
            for (int j = 0; j <= n; j += 1)
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

        for (int i = 1; i <= m; i += 1) {
            for (int j = 1; j <= n; j += 1) {
                if (a[i - 1] == b[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        char[] lcsString = new char[dp[m][n]];
        int i = m, j = n, k = dp[m][n];
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                lcsString[k - 1] = a[i - 1];
                k -= 1;
                i -= 1;
                j -= 1;
            } else if (dp[i - 1][j] > dp[i][j - 1])
                i -= 1;
            else
                j -= 1;
        }
        return String.valueOf(lcsString).toUpperCase();
    }

    public static void main(String[] args) {
        String X = "AGGTAB", Y = "GXTXAYB"; // LCS is GTAB
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();

        String sb =
                "Recursive : " + lcs.maxLCSLengthR(X, Y) + "\n" +
                "Memoized (Top-Down) : " + lcs.maxLCSLengthM(X, Y) + "\n" +
                "Tabulation (Bottom-Up) : " + lcs.maxLCSLengthT(X, Y) + "\n" +
                "LCS String : " + lcs.printLCS(X, Y);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
