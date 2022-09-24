package dp.kadane;

import java.io.PrintWriter;

class LargestSumContiguousIncreasingSubarray {
    public static void main(String[] args) {
        int[] nums = {10, 20, 30, 5, 10, 50};
        LargestSumContiguousIncreasingSubarray maxIncreasingSum = new LargestSumContiguousIncreasingSubarray();

        String res = "Bruteforce T(n) = O(n^2) : " + maxIncreasingSum.bruteforce(nums) + "\n" +
                "Using Kadane T(n) = O(n) : " + maxIncreasingSum.modifiedKadane(nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int[] nums) {
        int result = Integer.MIN_VALUE, n = nums.length;

        for (int i = 0; i < n; i++) {
            int localSum = nums[i], currMaxItem = nums[i];
            for (int j = i + 1; j < n; j++) {
                if (currMaxItem < nums[j]) {
                    localSum += nums[j];
                    currMaxItem = nums[j];
                } else {
                    localSum = 0;
                    continue;
                }
                result = Integer.max(result, localSum);
            }
        }

        return result;
    }

    private int modifiedKadane(int[] nums) {
        int result = Integer.MIN_VALUE, localSum, n = nums.length;

        for (int i = 0; i < n; i++) {
            localSum = nums[i];
            while (i + 1 < n && nums[i] < nums[i + 1]) {
                localSum += nums[i + 1];
                i += 1;
            }
            result = Integer.max(result, localSum);
        }

        return result;
    }
}
