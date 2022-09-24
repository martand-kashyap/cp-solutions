package dp.kadane;

import java.io.PrintWriter;
import java.util.HashSet;

class LargestSumContiguousUniqueElementsSubarray {
    /*-
        Given an array of N positive integers, the task is to find the subarray having the maximum sum among all subarrays
        having unique elements and print its sum.
    
        Input nums = [1, 2, 3, 3, 4, 5, 2, 1]
        Output: 15
        Explanation:
            The subarray having maximum sum with distinct element is [3, 4, 5, 2, 1].
            Therefore, sum is = 3 + 4 + 5 + 2 + 1 = 15
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 4, 5, 2, 1};

        LargestSumContiguousUniqueElementsSubarray maxUniqueElementsSum = new LargestSumContiguousUniqueElementsSubarray();

        String res = "Bruteforce T(n) = O(n^2) : " + maxUniqueElementsSum.bruteforce(nums) + "\n" +
                "Using Kadane T(n) = O(n) : " + maxUniqueElementsSum.modifiedKadane(nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int[] nums) {
        int result = Integer.MIN_VALUE, n = nums.length, localSum;
        HashSet<Integer> elementsAdded = new HashSet<>();

        for (int i = 0; i < n; i++) {
            localSum = 0;
            for (int j = i; j < n; j++) {
                if (elementsAdded.contains(nums[j])) {
                    elementsAdded.clear();
                    localSum = nums[j];
                } else {
                    localSum += nums[j];
                }
                elementsAdded.add(nums[j]);

                result = Integer.max(result, localSum);
            }
        }

        return result;
    }

    private int modifiedKadane(int[] nums) {
        int result = Integer.MIN_VALUE, n = nums.length, localSum = 0;
        HashSet<Integer> elementsAdded = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (!elementsAdded.contains(nums[i])) {
                localSum += nums[i];
                elementsAdded.add(nums[i]);

                result = Integer.max(result, localSum);
                localSum = Math.max(localSum, 0);
            }
        }

        return result;
    }
}
