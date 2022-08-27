package dp.fibonacci;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
class HouseRobber {
    private int robHousesR(int[] wealth) {
        return solveR(wealth, 0, wealth.length);
    }

    private int solveR(int[] wealth, int i, int n) {
        if (i >= n) return 0;

        int robCurrent = solveR(wealth, i + 2, n) + wealth[i];
        int skipCurrent = solveR(wealth, i + 1, n);

        return Integer.max(robCurrent, skipCurrent);
    }

    private int robHousesM(int[] wealth) {
        int[] dp = new int[wealth.length + 1];
        Arrays.fill(dp, -1);

        return memoize(wealth, 0, wealth.length, dp);
    }

    private int memoize(int[] wealth, int i, int n, int[] dp) {
        if (i >= n) return 0;
        if (dp[i] != -1) return dp[i];

        int robCurrent = solveR(wealth, i + 2, n) + wealth[i];
        int skipCurrent = solveR(wealth, i + 1, n);

        return dp[i] = Integer.max(robCurrent, skipCurrent);
    }

    private int robHousesT(int[] wealth) {
        if (wealth.length == 0) return 0;

        int[] dp = new int[wealth.length + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;          //no houses => no stealing => no profit
        dp[1] = wealth[0];  //only one house => rob it

        for (int i = 1; i < wealth.length; i++) {
            int robCurrent = dp[i - 1] + wealth[i];
            int skipCurrent = dp[i];
            dp[i + 1] = Integer.max(robCurrent, skipCurrent);
        }

        return dp[wealth.length];
    }

    public static void main(String[] args) {
        int[] wealth = {1, 2, 3, 1};
        HouseRobber hr = new HouseRobber();

        String sb =
                "Recursive : " + hr.robHousesR(wealth) + "\n" +
                "Memoized (Top-Down) : " + hr.robHousesM(wealth) + "\n" +
                "Tabulation (Bottom-Up) : " + hr.robHousesT(wealth);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
