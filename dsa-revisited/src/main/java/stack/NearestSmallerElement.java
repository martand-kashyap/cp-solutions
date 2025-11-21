package stack;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;

class NearestSmallerElement {
    public static void main(String[] args) {
        int[] arr = {6, 8, 0, 1, 3};

        NearestSmallerElement nse = new NearestSmallerElement();
        int[] nsr = nse.onRight(arr, arr.length);
        int[] nsl = nse.onLeft(arr, arr.length);

        String res = "Nearest Smaller Element (on right)\n" + Arrays.toString(nsr) +
                "\nNearest Smaller Element (on left)\n" + Arrays.toString(nsl);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] onRight(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        int[] nsr = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > arr[i])
                stack.pop();

            if (stack.isEmpty())
                nsr[i] = -1;
            else
                nsr[i] = stack.peek();

            stack.push(arr[i]);
        }

        return nsr;
    }

    private int[] onLeft(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        int[] nsl = new int[n];

        for (int i = 0; i < n; i += 1) {
            while (!stack.isEmpty() && stack.peek() > arr[i])
                stack.pop();

            if (stack.isEmpty())
                nsl[i] = -1;
            else
                nsl[i] = stack.peek();

            stack.push(arr[i]);
        }

        return nsl;
    }
}
