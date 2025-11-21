package stack;

import java.io.PrintWriter;
import java.util.Stack;

class LargestAreaUnderHistogram {
    public static void main(String[] args) {
        int[] heights = {6, 2, 5, 4, 5, 1, 6};
        LargestAreaUnderHistogram lauh = new LargestAreaUnderHistogram();
        String res = "Largest area under the given histogram: " + lauh.getMaxAreaUnderHistogram(heights);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    int getMaxAreaUnderHistogram(int[] heights) {
        int maxArea = Integer.MIN_VALUE;
        int[] nsl = new int[heights.length];
        int[] nsr = new int[heights.length];
        Stack<Integer> stack = new Stack<>();

        // find nearest smallest on the LHS of each element
        for (int i = 0; i < heights.length; i += 1) {
            while (!stack.empty() && heights[i] <= heights[stack.peek()])
                stack.pop();

            if (stack.empty())
                nsl[i] = -1;
            else
                nsl[i] = stack.peek();

            stack.push(i);
        }

        stack.clear();
        // find nearest smallest on the RHS of each element
        for (int i = heights.length - 1; i >= 0; i -= 1) {
            while (!stack.empty() && heights[i] <= heights[stack.peek()])
                stack.pop();

            if (stack.empty())
                nsr[i] = heights.length;
            else
                nsr[i] = stack.peek();

            stack.push(i);
        }

        for (int i = 0; i < heights.length; i += 1) {
            int width = nsr[i] - nsl[i] - 1;
            int area = width * heights[i];

            maxArea = Integer.max(maxArea, area);
        }

        return maxArea;
    }
}
