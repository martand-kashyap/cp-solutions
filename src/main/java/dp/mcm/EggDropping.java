package dp.mcm;

import java.io.PrintWriter;
import java.util.Arrays;

class EggDropping {
    /*-
        The following is a description of the instance of this famous puzzle involving N=2 eggs and a building with H=36 floors.

        Suppose that we wish to know which stories in a 36-story building are safe to drop eggs from,
        and which will cause the eggs to break on landing (using U.S. English terminology, in which the first floor is at ground level).

        We make a few assumptions:
            1. An egg that survives a fall can be used again.
            2. A broken egg must be discarded.
            3. The effect of a fall is the same for all eggs.
            4. If an egg breaks when dropped, then it would break if dropped from a higher window.
            5. If an egg survives a fall, then it would survive a shorter fall.
            6. It is not ruled out that the first-floor windows break eggs, nor is it ruled out that eggs can survive the 36th-floor windows.

        If only one egg is available and we wish to be sure of obtaining the right result, the experiment can be carried out in only one way.
        Drop the egg from the first-floor window; if it survives, drop it from the second-floor window.
        Continue upward until it breaks. In the worst case, this method may require 36 droppings.

        Suppose 2 eggs are available. What is the lowest number of egg-droppings that is guaranteed to work in all cases?
        To derive a dynamic programming functional equation for this puzzle,
        let the state of the dynamic programming model be a pair s = (n,k),
        where
            n = number of test eggs available, n = 0, 1, 2, 3, ..., N − 1.
            k = number of (consecutive) floors yet to be tested, k = 0, 1, 2, ..., H − 1.

        For instance, s = (2,6) indicates that two test eggs are available and 6 (consecutive) floors are yet to be tested.
        The initial state of the process is s = (N,H)
        where
            N denotes the number of test eggs available at the commencement of the experiment.
            The process terminates either when there are no more test eggs (n = 0) or when k = 0, whichever occurs first.
            If termination occurs at state s = (0,k) and k > 0, then the test failed.

        Now, let
            W(n,k) = minimum number of trials required to identify the value of the critical floor
                     under the worst-case scenario given that the process is in state s = (n,k).
        Then it can be shown that
            W(n,k) = 1 + min{max(W(n − 1, x − 1), W(n, k − x)): x = 1, 2, ..., k }
            with
            W(n,0) = 0 for all n > 0 and
            W(1,k) = k for all k.
        It is easy to solve this equation iteratively by systematically increasing the values of n and k.
    */
    public static void main(String[] args) {
        int eggs = 3, floors = 10;
        EggDropping ed = new EggDropping();
        String res = "Using recursion T(n) = O(2^n) : " + ed.minimumAttemptsR(eggs, floors) + "\n"
                + "Memoized (Top-Down) T(n) = O(n^3) : " + ed.minimumAttemptsM(eggs, floors);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int minimumAttemptsR(int e, int f) {
        return solveR(e, f);
    }

    private int solveR(int e, int f) {
        //If there is only one egg, start from the bottom floor & keep throwing eggs until it breaks.
        if (e == 1)
            return f;

        //0 floors => no need to try
        //1 floor  => only 1 way possible, throw egg, it will either break or not break
        if (f == 0 || f == 1)
            return f;

        int minNumberOfAttempts = Integer.MAX_VALUE;

        for (int k = 1; k <= f; k += 1) {
            /*
                An egg is thrown & it breaks
                => any floor above the current floor will also cause the egg to break
                => discard the broken egg & look for solutions below the current floor
             */
            int eggBreak = solveR(e - 1, k - 1);

            /*
                An egg is thrown & it does not break
                => any floor below the current floor also will not cause the egg to break.
                => re-use the current egg & look for solutions above the current floor
             */
            int eggNotBreak = solveR(e, f - k);

            int attemptCount = 1 + Math.max(eggBreak, eggNotBreak);

            // Minimise the number of attempts
            minNumberOfAttempts = Math.min(minNumberOfAttempts, attemptCount);
        }

        return minNumberOfAttempts;
    }

    private int minimumAttemptsM(int e, int f) {
        int[][] dp = new int[e + 1][f + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveM(e, f, dp);
    }

    private int solveM(int e, int f, int[][] dp) {
        if (e == 1)
            return f;

        if (f == 0 || f == 1)
            return f;

        if (dp[e][f] != -1)
            return dp[e][f];

        int minNumberOfAttempts = Integer.MAX_VALUE;

        for (int k = 1; k <= f; k += 1) {
            int eggBreak = dp[e - 1][k - 1] != -1 ? dp[e - 1][k - 1] : solveM(e - 1, k - 1, dp);
            int eggNotBreak = dp[e][f - k] != -1 ? dp[e][f - k] : solveM(e, f - k, dp);
            int attemptCount = 1 + Math.max(eggBreak, eggNotBreak);

            minNumberOfAttempts = Math.min(minNumberOfAttempts, attemptCount);
        }

        return dp[e][f] = minNumberOfAttempts;
    }
}
