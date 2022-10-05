package twopointers.slidingwindow.variable;

import java.io.PrintWriter;
import java.util.Arrays;

class LargestSubArrayOfSumK {
    /*-
    Given an array arr[] of size n containing integers.
    The problem is to find the length of the longest sub-array having sum equal to the given value k.

        Input: arr[] = { 10, 5, 2, 7, 1, 9 }, k = 15
        Output: 4
        Explanation: The sub-array is {5, 2, 7, 1}.
     */
    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 7, 1, 9};
        int k = 15;

        LargestSubArrayOfSumK lsosk = new LargestSubArrayOfSumK();

        String res =
                "Length of the longest sub-array having sum equal to the value k = " + k +
                        " in the given array " + "\n" + Arrays.toString(nums) + "\n\n" +
                        "Bruteforce T(n) = O(n^2) : " + lsosk.solveB(nums, k) + "\n" +
                        "Sliding Window T(n) = O(n) : " + lsosk.solveSW(nums, k);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int solveB(int[] nums, int k) {
        int n = nums.length, result = 0, sumOfElements;

        for (int i = 0; i < n; i++) {
            sumOfElements = 0;
            for (int j = i; j < n; j++) {
                sumOfElements += nums[j];
                if (sumOfElements == k)
                    result = Integer.max(result, j - i + 1);
            }
        }

        return result;
    }

    private int solveSW(int[] nums, int k) {
        int n = nums.length, result = 0, start = 0, end = 0, sumOfElementsInSW = 0;

        while (end < n) {
            sumOfElementsInSW += nums[end];

            if (sumOfElementsInSW < k)
                end += 1;
            else if (sumOfElementsInSW > k) {
                while (sumOfElementsInSW > k) {
                    sumOfElementsInSW -= nums[start];
                    start += 1;
                }
                end += 1;
            } else {
                result = Integer.max(result, end - start + 1);
                end += 1;
            }
        }

        return result;
    }
}
