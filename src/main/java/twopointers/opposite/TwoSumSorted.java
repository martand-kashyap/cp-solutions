package twopointers.opposite;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TwoSumSorted {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int sum = 8;

        TwoSumSorted problem = new TwoSumSorted();
        String res = "The pairs with the sum: " + sum + "\n" +
                "in the array : " + Arrays.toString(nums) + "\n\n" +
                "does a pair exists : " + problem.pairExistsWithSum(nums, sum) + "\n" +
                "count of such pairs : " + problem.countPairsWithSum(nums, sum) + "\n" +
                "such pairs : " + problem.getAllPairsWithSum(nums, sum);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }


    private int countPairsWithSum(int[] nums, int sum) {
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

    private boolean pairExistsWithSum(int[] nums, int sum) {
        int n = nums.length, l = 0, r = n - 1;

        while (l < r) {
            int currPairSum = nums[l] + nums[r];
            if (currPairSum < sum)
                l++;
            else if (nums[l] + nums[r] > sum)
                r--;
            else
                return true;

        }
        return false;
    }

    private List<List<Integer>> getAllPairsWithSum(int[] nums, int sum) {
        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length, l = 0, r = n - 1;

        while (l < r) {
            int currPairSum = nums[l] + nums[r];
            if (currPairSum < sum) {
                l++;
            } else if (nums[l] + nums[r] > sum) {
                r--;
            } else {
                List<Integer> pair = new ArrayList<>();
                pair.add(nums[l]);
                pair.add(nums[r]);

                res.add(pair);
                l++;
                r--;
            }
        }

        return res;
    }
}