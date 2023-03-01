package twopointers.opposite;

import java.io.PrintWriter;
import java.util.Arrays;

class ContainerWithMostWater {
    /*-
            Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
            n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
            Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

            Input:	height = [1,8,6,2,5,4,8,3,7]
            Output:	49
            Explanation:
                The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
                In this case, the max area of water (blue section) the container can contain is 49.
        */
    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        ContainerWithMostWater problem = new ContainerWithMostWater();
        String res = "Max water that can be contained in the heights : \n" + Arrays.toString(heights) + "\n" +
                "2 pointers : " + problem.solve2P(heights);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int solve2P(int[] heights) {
        int maxContent = 0;

        if (heights.length == 0) {
            return maxContent;
        }

        int left = 0, right = heights.length - 1;

        while (left < right) {
            int width = right - left;
            int waterLevel = Integer.min(heights[left], heights[right]);

            int currentWaterContent = width * waterLevel;
            maxContent = Integer.max(maxContent, currentWaterContent);

            if (heights[left] < heights[right]) {
                left += 1;
            } else {
                right -= 1;
            }
        }

        return maxContent;
    }
}
