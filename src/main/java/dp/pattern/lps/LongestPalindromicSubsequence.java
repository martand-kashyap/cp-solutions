package dp.pattern.lps;

import dp.pattern.lcs.LongestCommonSubsequence;

import java.io.PrintWriter;

class LongestPalindromicSubsequence {
    protected final LongestCommonSubsequence lcs;

    public LongestPalindromicSubsequence() {
        lcs = new LongestCommonSubsequence();
    }

    public static void main(String[] args) {
        String X = "GEEKSFORGEEKS"; // LPS is EEGEE
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();

        String sb =
                "Tabulation (Bottom-Up) using LCS : " + lps.maxLPSLengthUsingLCS(X) + "\n" +
                        "Longest Palindromic Subsequence : " + lps.printLPSUsingLCS(X);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }

    private int maxLPSLengthR(String x) {
//        return solveR(x, 0, x.length() - 1);
        return -1;
    }

    private int maxLPSLengthUsingLCS(String x) {
        String revX = new StringBuffer(x).reverse().toString();
        return lcs.maxLCSLengthT(x, revX);
    }

    private String printLPSUsingLCS(String x) {
        String revX = new StringBuffer(x).reverse().toString();
        return lcs.printLCS(x, revX);
    }
}
