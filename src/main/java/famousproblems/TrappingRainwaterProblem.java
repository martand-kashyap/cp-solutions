package famousproblems;

import java.io.PrintWriter;
import java.util.Arrays;

class TrappingRainwaterProblem {
    /*-
    Given an array of N non-negative integers arr[] representing an elevation map where the width of each bar is 1,
    compute how much water it is able to trap after raining.
     */
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        TrappingRainwaterProblem trp = new TrappingRainwaterProblem();
        String res = "Total trapped rainwater (DP approach): " + trp.usingDP(arr)
                + "\nTotal trapped rainwater (2 pointer approach): " + trp.using2Pointers(arr);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private long usingDP(int[] heights) {
        long[] waterTrappedOnBuilding = new long[heights.length];
        int[] maxOnRight = new int[heights.length];
        int[] maxOnLeft = new int[heights.length];
        long totalWaterTrapped;

        // calculate maxOnLeft[i]
        maxOnLeft[0] = heights[0];
        for (int i = 1; i < heights.length; i++) {
            maxOnLeft[i] = Integer.max(maxOnLeft[i - 1], heights[i]);
        }

        // calculate maxOnLeft[i]
        maxOnRight[heights.length - 1] = heights[heights.length - 1];
        for (int i = heights.length - 2; i >= 0; i--) {
            maxOnRight[i] = Integer.max(maxOnRight[i + 1], heights[i]);
        }

        for (int i = 0; i < heights.length; i++) {
            waterTrappedOnBuilding[i] = Integer.min(maxOnRight[i], maxOnLeft[i]) - heights[i];
        }

        totalWaterTrapped = Arrays.stream(waterTrappedOnBuilding).sum();
        return totalWaterTrapped;
    }

    private int using2Pointers(int[] heights) {
        int totalWaterTrapped = 0, maxLeft = 0, maxRight = 0, l = 0, r = heights.length - 1;

        while (l < r) {
            if (maxLeft < heights[l]) {
                maxLeft = heights[l];
            }

            if (maxRight < heights[r]) {
                maxRight = heights[r];
            }

            if (maxLeft < maxRight) {
                totalWaterTrapped += maxLeft - heights[l];
                l += 1;
            } else {
                totalWaterTrapped += maxRight - heights[r];
                r -= 1;
            }
        }
        return totalWaterTrapped;
    }
}
