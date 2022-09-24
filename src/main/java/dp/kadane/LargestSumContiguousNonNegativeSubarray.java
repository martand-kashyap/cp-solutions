package dp.kadane;

import java.io.PrintWriter;

class LargestSumContiguousNonNegativeSubarray {
    /*-
        Given an integer array nums[], the task is to find the largest sum contiguous subarray of non-negative elements
        and return its sum.

        Input: arr[] = {1, 4, -3, 9, 5, -6}
        Output: 14
        Explanation:
            Subarray [9, 5] is the subarray having maximum sum with all non-negative elements.
    */
    public static void main(String[] args) {
        int[] nums = {1, 4, -3, 9, 5, -6};
        LargestSumContiguousNonNegativeSubarray maxNonNegativeSum = new LargestSumContiguousNonNegativeSubarray();

        String res = "Bruteforce T(n) = O(n^2) : " + maxNonNegativeSum.bruteforce(nums) + "\n" +
                "Using Kadane T(n) = O(n) : " + maxNonNegativeSum.modifiedKadane(nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int[] nums) {
        int result = Integer.MIN_VALUE, n = nums.length;

        for (int i = 0; i < n; i++) {
            int localSum = nums[i] >= 0 ? nums[i] : 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] >= 0)
                    localSum += nums[j];
                else
                    localSum = 0;
                result = Integer.max(result, localSum);
            }
        }

        return result;
    }

    private int modifiedKadane(int[] nums) {
        int result = Integer.MIN_VALUE, n = nums.length, localSum = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0)
                localSum += nums[i];
            else
                localSum = 0;
            result = Integer.max(result, localSum);
        }

        return result;
    }
}
