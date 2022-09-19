package dp.kadane;

import java.io.PrintWriter;

class MaximumSubarraySum {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = nums.length;
        MaximumSubarraySum mss = new MaximumSubarraySum();

        String res = "Bruteforce T(n) = O(n^3) : " + mss.bruteforce(n, nums) + "\n" +
                "Bruteforce Optimized T(n) = O(n^2) : " + mss.bruteforceO(n, nums) + "\n" +
                "Kadane T(n) = O(n) : " + mss.kadaneAlgorithm(n, nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int n, int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for (int start = 0; start < n; start += 1) {
            for (int end = start; end < n; end += 1) {
                int sum = 0;
                for (int k = start; k < end; k += 1) {
                    sum += nums[k];
                    maxSum = Math.max(maxSum, sum);
                }
            }
        }

        return maxSum;
    }

    private int bruteforceO(int n, int[] nums) {
        int maxSubarray = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int currentSubarray = 0;
            for (int j = i; j < n; j++) {
                currentSubarray += nums[j];
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
        }

        return maxSubarray;
    }

    int kadaneAlgorithm(int n, int[] nums) {
        int maxSubarray = Integer.MIN_VALUE, currentSubarray = 0;

        for (int i = 0; i < n; i++) {
            currentSubarray += nums[i];
            maxSubarray = Math.max(maxSubarray, currentSubarray);
            currentSubarray = Math.max(currentSubarray, 0);
        }

        return maxSubarray;
    }
}
