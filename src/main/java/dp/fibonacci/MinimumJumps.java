package dp.fibonacci;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 */
class MinimumJumps {
    private int minJumpsR(int[] jumpLengths) {
        return solveR(jumpLengths, 0, jumpLengths.length - 1);
    }

    private int solveR(int[] jumpLengths, int l, int h) {
        if (l >= h) return 0;
        if (jumpLengths[l] == 0) return Integer.MAX_VALUE;

        int minNumberOfJumps = Integer.MAX_VALUE;
        for (int i = 1; i <= jumpLengths[l] && i <= h; i += 1) {
            int numberOfJumps = solveR(jumpLengths, l + i, h);
            if (numberOfJumps != Integer.MAX_VALUE)
                minNumberOfJumps = Integer.min(minNumberOfJumps, numberOfJumps + 1);
        }
        return minNumberOfJumps;
    }

    private int minJumpsM(int[] jumpLengths) {
        int[] dp = new int[jumpLengths.length + 1];
        Arrays.fill(dp, -1);

        return memoize(jumpLengths, 0, jumpLengths.length - 1, dp);
    }

    private int memoize(int[] jumpLengths, int l, int h, int[] dp) {
        if (l >= h) return 0;
        if (jumpLengths[l] == 0) return Integer.MAX_VALUE;
        if (dp[l] != -1) return dp[l];

        int minNumberOfJumps = Integer.MAX_VALUE;
        for (int i = 1; i <= jumpLengths[l] && i <= h; i += 1) {
            int numberOfJumps = memoize(jumpLengths, l + i, h, dp);
            if (numberOfJumps != Integer.MAX_VALUE)
                minNumberOfJumps = Integer.min(minNumberOfJumps, numberOfJumps + 1);
        }
        return dp[l] = minNumberOfJumps;
    }

    private int minJumpsT(int[] jumpLengths) {
        int[] dp = new int[jumpLengths.length+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int l = 0; l <= jumpLengths.length - 2; l++) {
            for (int h = l + 1; h <= l + jumpLengths[l] && h <= jumpLengths.length - 1; h++) {
                dp[h] = Integer.min(dp[h], dp[l] + 1);
            }
        }
        return dp[jumpLengths.length - 1];
    }

    public static void main(String[] args) {
        int[] jumpLengths = {2, 3, 1, 1, 4};
        MinimumJumps mj = new MinimumJumps();

        String sb =
                "Recursive : " + mj.minJumpsR(jumpLengths) + "\n" +
                "Memoized (Top-Down) : " + mj.minJumpsM(jumpLengths) + "\n" +
                "Tabulation (Bottom-Up) : " + mj.minJumpsT(jumpLengths);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
