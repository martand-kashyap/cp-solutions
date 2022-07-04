package dp.pattern.fibonacci;

import java.io.PrintWriter;
import java.util.Arrays;

class FibonacciNumbers {
    private int getNthFibonacciNumberR(int n) {
        if (n <= 1) return n;
        return getNthFibonacciNumberR(n - 1) + getNthFibonacciNumberR(n - 2);
    }

    private int getNthFibonacciNumberM(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return memoize(n, dp);
    }

    private int memoize(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = memoize(n - 1, dp) + memoize(n - 2, dp);
    }

    private int getNthFibonacciNumberT(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    private int optimized(int n) {
        int a = 0, b = 1, c = -1;

        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }

    public static void main(String[] args) {
        int n = 10;
        FibonacciNumbers fn = new FibonacciNumbers();

        String sb =
                "Recursive : " + fn.getNthFibonacciNumberR(n) + "\n" +
                "Memoized (Top-Down) : " + fn.getNthFibonacciNumberM(n) + "\n" +
                "Tabulation (Bottom-Up) : " + fn.getNthFibonacciNumberT(n) + "\n" +
                "Optimized : " + fn.optimized(n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
