package dp.kadane;

import java.io.PrintWriter;

class MaxDiff0sAnd1sBinaryString {
    public static void main(String[] args) {
        String input = "11000010001";
        MaxDiff0sAnd1sBinaryString maxDiff = new MaxDiff0sAnd1sBinaryString();

        String res =
                "Bruteforce T(n) = O(n^2) : " + maxDiff.bruteforce(input, input.length()) + "\n" +
                        "Using Kadane T(n) = O(n) : " + maxDiff.modifiedKadane(input, input.length());

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(String input, int n) {
        int globalBest = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int localBest = 0;
            for (int j = i; j < n; j++) {
                localBest += (input.charAt(j) == '0') ? 1 : -1;
                globalBest = Integer.max(globalBest, localBest);
            }
        }

        return globalBest;
    }

    private int modifiedKadane(String input, int n) {
        int globalBest = Integer.MIN_VALUE, localBest = 0;

        for (int i = 0; i < n; i++) {
            localBest += (input.charAt(i) == '0') ? 1 : -1;
            localBest = Integer.max(localBest, 0);
            globalBest = Integer.max(globalBest, localBest);
        }

        return globalBest;
    }
}
