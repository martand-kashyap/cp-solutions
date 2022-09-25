package dp.lcs;

import java.io.PrintWriter;

class ShortestCommonSupersequence {
    public static void main(String[] args) {
        String X = "dynamic", Y = "programming";
        ShortestCommonSupersequence scs = new ShortestCommonSupersequence();

        String response = "Shortest Common Supersequence of \n\t\"" + X.toUpperCase() + "\" & \"" + Y.toUpperCase() +
                "\"\nis : " + scs.solveT(X, Y) + "\n\n" +
                "Required super-sequence : " + scs.printSupersequence(X, Y);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(response);
        pw.close();
    }

    private int solveT(String x, String y) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        int lcsLength = lcs.maxLCSLengthT(x, y);

        return (x.length() + y.length() - lcsLength);
    }

    private String printSupersequence(String x, String y) {
        //Find the LCS of the input strings
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

        //find the length of the super-sequence
        int lcsLength = dp[m][n], scsLength = x.length() + y.length() - lcsLength;
        //assign pointers for X, Y, & SCS
        int i = m, j = n, k = scsLength;

        //initialize the super-sequence buffer
        char[] superSequence = new char[scsLength];

        //when loop breaks either i or j would not be 0
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                superSequence[k - 1] = a[i - 1];
                i -= 1;
                j -= 1;
            } else if (dp[i][j - 1] >= dp[i - 1][j]) {
                superSequence[k - 1] = b[j - 1];
                j -= 1;
            } else if (dp[i][j - 1] < dp[i - 1][j]) {
                superSequence[k - 1] = a[i - 1];
                i -= 1;
            }
            k -= 1;
        }

        //take all remaining  elements from X
        while (i > 0) {
            superSequence[k - 1] = a[i - 1];
            i -= 1;
            k -= 1;
        }

        //take all remaining  elements from Y
        while (j > 0) {
            superSequence[k - 1] = b[j - 1];
            j -= 1;
            k -= 1;
        }

        return String.valueOf(superSequence).toUpperCase();
    }
}
