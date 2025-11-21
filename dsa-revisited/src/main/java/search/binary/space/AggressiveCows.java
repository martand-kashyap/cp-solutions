package search.binary.space;

import java.io.PrintWriter;
import java.util.Arrays;

class AggressiveCows {
    /*-
    Farmer John has built a new long barn with N stalls.
    The stalls are located along a straight line at positions x1,...,xN.

    His C (2 <= C <= N) cows don't like this barn layout
    and become aggressive towards each other once put into a stall.

    To prevent the cows from hurting each other,
    John wants to assign the cows to the stalls,
    such that the minimum distance between any two of them is as large as possible.

    What is the largest minimum distance?
    */

    public static void main(String[] args) {
        int[] stalls = {1, 2, 8, 4, 9};
        int n = stalls.length, numberOfCows = 3;

        AggressiveCows ac = new AggressiveCows();
        String res = "Maximized(min distance b/w cows) : " + ac.maximizeDistanceBetweenCows(stalls, n, numberOfCows);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int maximizeDistanceBetweenCows(int[] stalls, int n, int numberOfCows) {
        if (numberOfCows > n)
            return -1;

        int max = Arrays.stream(stalls).max().getAsInt();
        int min = Arrays.stream(stalls).min().getAsInt();
        int l = 1, r = max - min, result = l;

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (isAllocationPossible(stalls, n, numberOfCows, m)) {
                result = m;
                l = m + 1;
            } else
                r = m - 1;
        }

        return result;
    }

    private boolean isAllocationPossible(int[] stalls, int n, int numberOfCows, int requiredMinDistance) {
        Arrays.sort(stalls);

        //keep 1 cow in the 1st stall
        int cowsInStalls = 1, lastCowPlacedInStall = stalls[0];

        for (int i = 1; i < n; i += 1) {
            if (stalls[i] - lastCowPlacedInStall >= requiredMinDistance) {
                cowsInStalls += 1;
                lastCowPlacedInStall = stalls[i];
            }

            if (cowsInStalls >= numberOfCows)
                return true;
        }

        return false;
    }
}
