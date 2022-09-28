package twopointers.slidingwindow.fixed;

import java.io.PrintWriter;
import java.util.Arrays;

class MaxSumSubArrayOfGivenSize {
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 1, 3, 2};
        int k = 3;

        MaxSumSubArrayOfGivenSize swf = new MaxSumSubArrayOfGivenSize();

        String res = "Max of all sums in sub-arrays of size " + k + "\n" +
                "in the given array\n" + Arrays.toString(nums) + "\n\n" +
                "Bruteforce T(n) = O(n*k) : " + swf.getMaxSubArrayOfSizeKB(nums, k) + "\n" +
                "Sliding Window T(n) = O(n) : " + swf.getMaxSubArrayOfSizeSW(nums, k) + "\n" +
                "Sliding Window [alternative implementation] T(n) = O(n) : " + swf.getMaxSubArrayOfSizeSWAlternative(nums, k);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int getMaxSubArrayOfSizeKB(int[] nums, int k) {
        int n = nums.length, maxSum = Integer.MIN_VALUE;

        if (n < k)
            return -1;

        for (int i = 0; i < n - k; i++) {
            int subArraySum = 0;
            for (int j = i; j < i + k; j++) {
                subArraySum += nums[j];
                maxSum = Integer.max(maxSum, subArraySum);
            }
        }

        return maxSum;
    }

    private int getMaxSubArrayOfSizeSW(int[] nums, int k) {
        int n = nums.length, maxSum = Integer.MIN_VALUE, subArraySum = 0, start = 0, end = 0;

        if (n < k)
            return -1;

        while (end < n) {
            subArraySum += nums[end];

            //if size(currentWindow) < k, increase the right pointer
            int currentWindowSize = end - start + 1;
            if (currentWindowSize < k)
                end += 1;
            else if (currentWindowSize == k) {
                maxSum = Integer.max(maxSum, subArraySum);

                subArraySum -= nums[start];

                start += 1;
                end += 1;
            }
        }

        return maxSum;
    }

    private int getMaxSubArrayOfSizeSWAlternative(int[] nums, int k) {
        int n = nums.length, maxSum = Integer.MIN_VALUE, subArraySum = 0;

        if (n < k)
            return -1;

        for (int i = 0; i < k; i++)
            subArraySum += nums[i];

        for (int i = k; i < n; i++) {
            subArraySum += nums[i] - nums[i - k];
            maxSum = Integer.max(maxSum, subArraySum);
        }

        return maxSum;
    }
}
