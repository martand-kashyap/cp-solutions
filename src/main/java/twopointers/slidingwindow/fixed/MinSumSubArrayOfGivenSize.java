package twopointers.slidingwindow.fixed;

import java.io.PrintWriter;
import java.util.Arrays;

class MinSumSubArrayOfGivenSize {
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 1, 3, 2};
        int k = 3;

        MinSumSubArrayOfGivenSize swf = new MinSumSubArrayOfGivenSize();

        String res = "Min of all sums in sub-arrays of size " + k + "\n" +
                "in the given array\n" + Arrays.toString(nums) + "\n\n" +
                "Bruteforce T(n) = O(n*k) : " + swf.getMinSubArrayOfSizeKB(nums, k) + "\n" +
                "Sliding Window T(n) = O(n) : " + swf.getMinSubArrayOfSizeSW(nums, k) + "\n" +
                "Sliding Window [alternative implementation] T(n) = O(n) : " + swf.getMinSubArrayOfSizeSWAlternative(nums, k);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int getMinSubArrayOfSizeKB(int[] nums, int k) {
        int n = nums.length, minSum = Integer.MAX_VALUE;

        for (int i = 0; i < n - k + 1; i++) {
            int subArraySum = 0;
            for (int j = i; j < i + k; j++) {
                subArraySum += nums[j];
                if (j - i + 1 == k)
                    minSum = Integer.min(minSum, subArraySum);
            }
        }

        return minSum;
    }

    private int getMinSubArrayOfSizeSW(int[] nums, int k) {
        int n = nums.length, minSum = Integer.MAX_VALUE, subArraySum = 0, i = 0, j = 0;

        while (j < n) {
            subArraySum += nums[j];

            //if size(currentWindow) < k, increase the right pointer
            int currentWindowSize = j - i + 1;
            if (currentWindowSize < k)
                j += 1;
            else if (currentWindowSize == k) {
                minSum = Integer.min(minSum, subArraySum);

                subArraySum -= nums[i];

                i += 1;
                j += 1;
            }
        }

        return minSum;
    }

    private int getMinSubArrayOfSizeSWAlternative(int[] nums, int k) {
        int n = nums.length, minSum = Integer.MAX_VALUE, subArraySum = 0;

        for (int i = 0; i < k; i++)
            subArraySum += nums[i];

        for (int i = k; i < n; i++) {
            subArraySum += nums[i] - nums[i - k];
            minSum = Integer.min(minSum, subArraySum);
        }

        return minSum;
    }
}
