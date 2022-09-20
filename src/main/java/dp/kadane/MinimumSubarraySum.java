package dp.kadane;

import java.io.PrintWriter;

class MinimumSubarraySum {
    public static void main(String[] args) {
        int[] nums = {3, -4, 2, -3, -1, 7, -5};
        int n = nums.length;
        MinimumSubarraySum mss = new MinimumSubarraySum();

        String res = "Bruteforce T(n) = O(n^3) : " + mss.bruteforce(n, nums) + "\n" +
                "Bruteforce Optimized T(n) = O(n^2) : " + mss.bruteforceO(n, nums) + "\n" +
                "Modified Kadane T(n) = O(n) : " + mss.modifiedKadane(n, nums) + "\n" +
                "Inverted Kadane T(n) = O(n) : " + mss.invertedKadane(n, nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int n, int[] nums) {
        int minSum = Integer.MAX_VALUE;
        for (int start = 0; start < n; start += 1) {
            for (int end = start; end < n; end += 1) {
                int sum = 0;
                for (int k = start; k < end; k += 1) {
                    sum += nums[k];
                    minSum = Math.min(minSum, sum);
                }
            }
        }

        return minSum;
    }

    private int bruteforceO(int n, int[] nums) {
        int minSubarray = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int currentSubarray = 0;
            for (int j = i; j < n; j++) {
                currentSubarray += nums[j];
                minSubarray = Math.min(minSubarray, currentSubarray);
            }
        }

        return minSubarray;
    }

    int modifiedKadane(int n, int[] nums) {
        int minSubarray = Integer.MAX_VALUE, currentSubarray = 0;

        for (int i = 0; i < n; i++) {
            currentSubarray += nums[i];
            minSubarray = Math.min(minSubarray, currentSubarray);
            currentSubarray = Math.min(currentSubarray, 0);
        }

        return minSubarray;
    }

    private int invertedKadane(int n, int[] nums) {
        MaximumSubarraySum mss = new MaximumSubarraySum();

        for (int i = 0; i < n; i++) {
            nums[i] = (-1) * nums[i];
        }

        return (-1) * mss.kadaneAlgorithm(n, nums);
    }
}
