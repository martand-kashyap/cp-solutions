package dp.pattern.fibonacci;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
 * Once you pay the cost, you can either climb one or two steps.
 * You can either start from the step with index 0, or the step with index 1.
 * Return the minimum cost to reach the top of the floor.
 */

class ClimbingStairsMinimumCost {
    int getMinCostToClimbStairsR(int[] cost) {
        return solveR(cost, cost.length);
    }

    private int solveR(int[] cost, int i) {
        if (i == 0 || i == 1) return 0;

        int take1Step = solveR(cost, i - 1) + cost[i - 1];
        int take2Step = solveR(cost, i - 2) + cost[i - 2];

        return Math.min(take1Step, take2Step);
    }

    int getMinCostToClimbStairsM(int[] cost) {
        int[] dp = new int[cost.length + 1];
        Arrays.fill(dp, -1);

        return memoize(cost, cost.length, dp);
    }

    private int memoize(int[] cost, int i, int[] dp) {
        if (i == 0 || i == 1) return 0;
        if (dp[i] != -1) return dp[i];

        int take1Step = memoize(cost, i - 1, dp) + cost[i - 1];
        int take2Step = memoize(cost, i - 2, dp) + cost[i - 2];

        return dp[i] = Math.min(take1Step, take2Step);
    }

    int getMinCostToClimbStairsT(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = dp[1] = 0;

        for (int i = 2; i <= cost.length; i++) {
            int take1Step = dp[i - 1] + cost[i - 1];
            int take2Step = dp[i - 2] + cost[i - 2];

            dp[i] = Math.min(take1Step, take2Step);
        }

        return dp[cost.length];
    }

//    private int optimized(int[] cost) {
//        int a = 0, b = 0, c = 0;
//
//        for (int i = 2; i <= cost.length; i++) {
//            int take1Step = a + cost[i - 1];
//            int take2Step = b + cost[i - 2];
//            c = Math.min(take1Step, take2Step);
//            a = b;
//            b = c;
//        }
//
//        return c;
//    }

    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        ClimbingStairsMinimumCost csmc = new ClimbingStairsMinimumCost();

        String sb =
                "Recursive : " + csmc.getMinCostToClimbStairsR(cost) + "\n" +
                "Memoized (Top-Down) : " + csmc.getMinCostToClimbStairsM(cost) + "\n" +
                "Tabulation (Bottom-Up) : " + csmc.getMinCostToClimbStairsT(cost);
//        sb.append("Optimized : " + csmc.optimized(cost));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
