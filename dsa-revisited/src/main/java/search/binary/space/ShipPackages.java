package search.binary.space;

import java.io.PrintWriter;
import java.util.Arrays;

class ShipPackages {
    public static void main(String[] args) {
        int[] weights = {1, 2, 1};
        int n = weights.length, d = 2;
        ShipPackages shipPackages = new ShipPackages();

        String res = "The least weight capacity of a boat to ship all weights within " + d + " days\n" + shipPackages.leastWeightCapacity(weights, n, d);
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    int leastWeightCapacity(int[] weights, int n, int maxDays) {
        int result = Integer.MAX_VALUE;
        int l = Arrays.stream(weights).max().getAsInt();
        int r = Arrays.stream(weights).sum();

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (isShippingPossible(weights, n, maxDays, m)) {
                result = m;
                r = m - 1;
            } else
                l = m + 1;
        }

        return result;
    }

    private boolean isShippingPossible(int[] weights, int n, int maxDays, int maxWeightAllowed) {
        int daysRequired = 1, weightSum = 0;

        for (int i = 0; i < n; i += 1) {
            weightSum += weights[i];

            if (weightSum > maxWeightAllowed) {
                daysRequired += 1;
                weightSum = weights[i];
            }

            if (daysRequired > maxDays) return false;
        }

        return true;
    }
}
