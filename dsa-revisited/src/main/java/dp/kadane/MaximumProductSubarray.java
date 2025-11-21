package dp.kadane;

import java.io.PrintWriter;

class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        MaximumProductSubarray mps = new MaximumProductSubarray();

        String res =
                "Bruteforce T(n) = O(n^2) : " + mps.bruteforce(nums, nums.length) + "\n" +
                        "Using Kadane T(n) = O(n) : " + mps.modifiedKadane(nums, nums.length);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int[] nums, int n) {
        int globalBest = nums[0];

        for (int i = 0; i < n; i++) {
            int localBest = nums[i];
            for (int j = i + 1; j < n; j++) {
                localBest *= nums[j];
                globalBest = Integer.max(globalBest, localBest);
            }
        }

        return globalBest;
    }

    private int modifiedKadane(int[] nums, int n) {
        if (n == 0)
            return 0;

        int maxSoFar = nums[0], minSoFar = nums[0];
        int result = maxSoFar;

        for (int i = 1; i < n; i++) {
            int currItem = nums[i];

            int tempMax = Math.max(
                    currItem,
                    Math.max(
                            maxSoFar * currItem,
                            minSoFar * currItem));

            minSoFar = Math.min(
                    currItem,
                    Math.min(
                            maxSoFar * currItem,
                            minSoFar * currItem));

            maxSoFar = tempMax;

            result = Math.max(maxSoFar, result);
        }

        return result;
    }
}
