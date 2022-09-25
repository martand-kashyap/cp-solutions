package dp.kadane;

import java.io.PrintWriter;

class MaximumSumCircularSubarray {
    /*-
    Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

    A circular array means the end of the array connects to the beginning of the array.
    Formally, the next element of nums[i] is nums[(i + 1) % n] and
    the previous element of nums[i] is nums[(i - 1 + n) % n].

    A subarray may only include each element of the fixed buffer nums at most once.
    Formally, for a subarray nums[i], nums[i + 1], ..., nums[j],
    there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

    Input: nums = [1,-2,3,-2]
    Output: 3
    Explanation: sub-array [3] has maximum sum 3.
     */
    public static void main(String[] args) {
        int[] nums = {-3, -18, -22, -21, -17, 16, -14, 28, -22};
        MaximumSumCircularSubarray mscs = new MaximumSumCircularSubarray();

        String res = "Using Kadane T(n) = O(n) : " + mscs.modifiedKadane(nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int modifiedKadane(int[] nums) {
        int n = nums.length, result, maxElement = Integer.MIN_VALUE, smallestSumSubarray, largetSumSubarray, totalSum = 0;
        MaximumSubarraySum mss;

        //Return the largest negative number, if all numbers are negative
        for (int x : nums)
            maxElement = Integer.max(maxElement, x);

        if (maxElement < 0)
            return maxElement;


        //calculate the total sum
        for (int x : nums)
            totalSum += x;

        //find the largest sum contiguous sub-array using Kadane
        mss = new MaximumSubarraySum();
        largetSumSubarray = mss.kadaneAlgorithm(n, nums);

        //find the smallest sum contiguous sub-array using Kadane
        for (int i = 0; i < n; i++) {
            nums[i] = (-1) * nums[i];
        }
        smallestSumSubarray = mss.kadaneAlgorithm(n, nums);

        /*-
        the largest sum circular sub-array can be given by
        totalSum - smallestSubarraySum
         */
        result = Integer.max(largetSumSubarray, totalSum + smallestSumSubarray);

        return result;
    }
}
