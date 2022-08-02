package stack;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;

class StockSpan {
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};

        StockSpan ss = new StockSpan();
        int[] spans = ss.findSpans(prices);

        String res = "Spans are : " + Arrays.toString(spans);
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] findSpans(int[] price) {
        int[] spans = new int[price.length];
        Stack<Integer> stack = new Stack<>();

        //span for the 1st index is always 1.
        spans[0] = 1;
        //track the indices, not the values.
        stack.push(0);

        //look for nearest greater element on the left side of the current index
        for (int i = 1; i < price.length; i += 1) {
            while (!stack.empty() && price[i] >= price[stack.peek()])
                stack.pop();

            /*-
            if (stack.empty())
                spans[i] = i + 1;
            else
                spans[i] = i - stack.peek();
            */

            spans[i] = stack.empty() ? i + 1 : i - stack.peek();

            stack.push(i);
        }

        return spans;
    }
}
