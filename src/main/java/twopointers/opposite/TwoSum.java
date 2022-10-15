package twopointers.opposite;

import java.io.PrintWriter;
import java.util.*;

class TwoSum {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 1, 5, 6, 7};
        int targetSum = 8;

        TwoSum problem = new TwoSum();
        String res = "The pairs with the sum: " + targetSum + "\n" +
                "in the array : " + Arrays.toString(nums) + "\n\n" +
                "does a pair exists : " + problem.pairExistsWithSum(Arrays.copyOf(nums, nums.length), targetSum) + "\n" +
                "count of such pairs : " + problem.countPairsWithSum(Arrays.copyOf(nums, nums.length), targetSum) + "\n" +
                "the pairs are (2 pointers) : " + problem.getAllPairsWithSum2P(Arrays.copyOf(nums, nums.length), targetSum) + "\n" +
                "the pairs are (hashing) : " + problem.getAllPairsWithSumHashing(Arrays.copyOf(nums, nums.length), targetSum);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }


    private int countPairsWithSum(int[] nums, int targetSum) {
        int n = nums.length, l = 0, r = n - 1, count = 0;

        Arrays.sort(nums);

        while (l < r) {
            int currPairSum = nums[l] + nums[r];
            if (currPairSum < targetSum) {
                l++;
            } else if (nums[l] + nums[r] > targetSum) {
                r--;
            } else {
                count += 1;
                l++;
                r--;
            }
        }

        return count;
    }

    private boolean pairExistsWithSum(int[] nums, int targetSum) {
        int n = nums.length, l = 0, r = n - 1;

        Arrays.sort(nums);

        while (l < r) {
            int currPairSum = nums[l] + nums[r];
            if (currPairSum < targetSum)
                l++;
            else if (nums[l] + nums[r] > targetSum)
                r--;
            else
                return true;

        }
        return false;
    }

    private List<List<Integer>> getAllPairsWithSum2P(int[] nums, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length, l = 0, r = n - 1;

        Arrays.sort(nums);

        while (l < r) {
            int currPairSum = nums[l] + nums[r];

            if (currPairSum < targetSum) {
                l++;
            } else if (nums[l] + nums[r] > targetSum) {
                r--;
            } else {
                res.add(Arrays.asList(nums[l], nums[r]));

                l++;
                r--;
            }
        }

        return res;
    }

    private List<List<Integer>> getAllPairsWithSumHashing(int[] nums, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> numberOccurrenceMap = new HashMap<>();
        int n = nums.length;

        for (int x : nums) {
            int y = targetSum - x;

            if (numberOccurrenceMap.containsKey(y)) {
                res.add(Arrays.asList(y, x));
                continue;
            }

            numberOccurrenceMap.put(x, numberOccurrenceMap.getOrDefault(x, 0) + 1);
        }

        return res;
    }
}