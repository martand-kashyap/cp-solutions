package twopointers.opposite;

import java.io.PrintWriter;
import java.util.Arrays;

class TwoSumSorted {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int sum = 8;

        TwoSumSorted problem = new TwoSumSorted();
        String res = "The #pairs with the sum: " + sum + "\n" +
                "in the array : " + Arrays.toString(nums) + "\n\n" +
                "2 pointers : " + problem.countPairsWithSum(nums, sum);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }


    int countPairsWithSum(int[] nums, int sum) {
        int n = nums.length, l = 0, r = n - 1, count = 0;

        while (l < r) {
            int currPairSum = nums[l] + nums[r];
            if (currPairSum < sum) {
                l++;
            } else if (nums[l] + nums[r] > sum) {
                r--;
            } else {
                count += 1;
                l++;
                r--;
            }
        }

        return count;
    }
}