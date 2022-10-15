package twopointers.opposite;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int targetSum = 0;

        ThreeSum problem = new ThreeSum();

        String res =
                "The triplet with sum = " + targetSum + "\n" +
                        "in the array" + "\n" + Arrays.toString(nums) + "\n\n" +
                        "Bruteforce T(n) = O(n) : " + problem.solveB(nums, targetSum) + "\n" +
                        "Exists or not T(n) = O(n) : " + problem.tripletWithSumExists(nums, targetSum) + "\n" +
                        "2 Pointers T(n) = O(n) : " + problem.getAllTripletsWithSum2P(nums, targetSum);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private List<List<Integer>> solveB(int[] nums, int sum) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i])
                continue;

            for (int j = i + 1; j < nums.length - 1; j++)
                for (int k = j + 1; k < nums.length; k++)
                    if (nums[i] + nums[j] + nums[k] == sum)
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
        }

        return result;
    }

    private boolean tripletWithSumExists(int[] nums, int targetSum) {
        boolean tripletExists = false;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i])
                continue;

            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int currSum = nums[i] + nums[j] + nums[k];

                if (currSum == targetSum)
                    return true;
                else if (currSum < targetSum)
                    j += 1;
                else
                    k -= 1;
            }
        }

        return tripletExists;
    }

    private List<List<Integer>> getAllTripletsWithSum2P(int[] nums, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i])
                continue;

            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                if (nums[i] + nums[j] + nums[k] == targetSum) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    while (j < k && nums[j] == nums[j - 1])
                        j++;
                    while (j < k && nums[k] == nums[k + 1])
                        k--;
                } else if (nums[i] + nums[j] + nums[k] < 0)
                    j++;
                else
                    k--;
            }
        }

        return res;
    }
}
