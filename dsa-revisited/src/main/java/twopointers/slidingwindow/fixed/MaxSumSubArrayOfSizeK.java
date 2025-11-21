package twopointers.slidingwindow.fixed;

import java.io.PrintWriter;
import java.util.Arrays;

class MaxSumSubArrayOfSizeK {
    /*-
        Given an array of integers and a number k, find the maximum sum of a subarray of size k.

        Input  : arr[] = {100, 200, 300, 400},  k = 2
        Output : 700

        Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}, k = 4
        Output : 39
        Explanation: We get maximum sum by adding subarray {4, 2, 10, 23} of size 4.

        Input  : arr[] = {2, 3}, k = 3
        Output : Invalid
        Explanation: There is no subarray of size 3 as size of whole array is 2.
     */
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 1, 3, 2};
        int k = 3;

        MaxSumSubArrayOfSizeK problem = new MaxSumSubArrayOfSizeK();

        String res = "Max (sums of sub-arrays of size " + k + ") in the given array\n" + Arrays.toString(nums) + "\n\n" +
                "Bruteforce : " + problem.bruteforce(nums, k) + "\n" +
                "Sliding Window : " + problem.slidingWindowFixed(nums, k) + "\n" +
                "Sliding Window [alternative implementation] : " + problem.slidingWindowFixedAltImpl(nums, k);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int[] nums, int k) {
        int n = nums.length, maxSum = Integer.MIN_VALUE;

        if (n < k)
            return -1;

        for (int i = 0; i < n - k + 1; i++) {
            int subArraySum = 0;
            for (int j = i; j < i + k; j++) {
                subArraySum += nums[j];
                if (j - i + 1 == k)
                    maxSum = Integer.max(maxSum, subArraySum);
            }
        }

        //T(n) = O(n*k)
        return maxSum;
    }

    private int slidingWindowFixed(int[] nums, int k) {
        int n = nums.length, maxSum = Integer.MIN_VALUE, subArraySum = 0, start = 0, end = 0;

        if (n < k)
            return -1;

        while (end < n) {
            subArraySum += nums[end];

            //if size(currentWindow) < k, increase the right pointer
            int currentWindowSize = end - start + 1;

            /*-
             if desired window size of k is not yet achieved
             => keep increasing the size of the window
             */
            if (currentWindowSize < k) {
                end += 1;
            }

            /*-
             if desired window size of k is achieved
             => we will get the answer
             */
            else if (currentWindowSize == k) {
                maxSum = Integer.max(maxSum, subArraySum);

                //subtract the element which is going to leave the window
                subArraySum -= nums[start];

                //move the sw forward
                start += 1;
                end += 1;
            }
        }

        //T(n) = O(n)
        return maxSum;
    }

    private int slidingWindowFixedAltImpl(int[] nums, int k) {
        int n = nums.length, maxSum = Integer.MIN_VALUE, subArraySum = 0;

        if (n < k)
            return -1;

        //sum the first k elements
        for (int i = 0; i < k; i++)
            subArraySum += nums[i];

        for (int i = k; i < n; i++) {
            //add the element on right
            subArraySum += nums[i];

            //remove the element leaving the sliding window
            subArraySum -= nums[i - k];

            //get the answer
            maxSum = Integer.max(maxSum, subArraySum);
        }

        //T(n) = O(n)
        return maxSum;
    }
}
