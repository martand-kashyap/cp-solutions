package stack;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;

class NearestGreaterElement {
    public static void main(String[] args) {
        int[] arr = {6, 8, 0, 1, 3};

        NearestGreaterElement nge = new NearestGreaterElement();
        int[] ngr = nge.onRight(arr, arr.length);
        int[] ngl = nge.onLeft(arr, arr.length);

        String res = "Nearest Greater Element (on right)\n" + Arrays.toString(ngr) +
                "\nNearest Greater Element (on left)\n" + Arrays.toString(ngl);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] onRight(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < arr[i])
                stack.pop();

            if (stack.isEmpty())
                nge[i] = -1;
            else
                nge[i] = stack.peek();

            stack.push(arr[i]);
        }

        return nge;
    }

    private int[] onLeft(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[n];

        for (int i = 0; i < n; i += 1) {
            while (!stack.isEmpty() && stack.peek() < arr[i])
                stack.pop();

            if (stack.isEmpty())
                nge[i] = -1;
            else
                nge[i] = stack.peek();

            stack.push(arr[i]);
        }

        return nge;
    }
}
