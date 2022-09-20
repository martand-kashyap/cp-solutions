package dp.kadane;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

class MaximumSubarraySum {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = nums.length;

        MaximumSubarraySum mss = new MaximumSubarraySum();
        Integer[] maxSubArray = mss.findMaxSumSubArray(n, nums);
        String res =
                "Bruteforce T(n) = O(n^3) : " + mss.bruteforce(n, nums) + "\n" +
                        "Bruteforce Optimized T(n) = O(n^2) : " + mss.bruteforceO(n, nums) + "\n" +
                        "Kadane T(n) = O(n) : " + mss.kadaneAlgorithm(n, nums) + "\n" +
                        "Kadane (alternative implementation) T(n) = O(n) : " + mss.kadaneAlgorithmAlternative(n, nums) + "\n" +
                        "Max Sum Subarray : " + Arrays.toString(maxSubArray);

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
        int globalBestSum = Integer.MIN_VALUE, localBestSum = 0;

        for (int i = 0; i < n; i++) {
            localBestSum += nums[i];
            globalBestSum = Math.max(globalBestSum, localBestSum);
            localBestSum = Math.max(localBestSum, 0);
        }

        return globalBestSum;
    }

    int kadaneAlgorithmAlternative(int n, int[] nums) {
        int globalBestSum = nums[0], localBestSum = nums[0];

        for (int i = 1; i < n; i++) {
            if (localBestSum >= 0)
                localBestSum += nums[i];
            else
                localBestSum = nums[i];

            globalBestSum = Math.max(globalBestSum, localBestSum);
        }

        return globalBestSum;
    }

    private Integer[] findMaxSumSubArray(int n, int[] nums) {
        int globalBestSum = Integer.MIN_VALUE, localBestSum = 0, start = -1, end = -1;
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i += 1) {
            localBestSum += nums[i];
            if (globalBestSum < localBestSum) {
                globalBestSum = localBestSum;
                end = i;
            }
            localBestSum = Math.max(localBestSum, 0);
        }

        start = end;
        while (start >= 0) {
            globalBestSum -= nums[start];

            if (globalBestSum == 0)
                break;

            // Decrement the start index
            start--;
        }


        for (int i = start; i <= end; i += 1) {
            res.add(nums[i]);
        }

        return res.toArray(Integer[]::new);
    }
}
