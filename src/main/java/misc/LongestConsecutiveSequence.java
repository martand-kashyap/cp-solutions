package misc;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LongestConsecutiveSequence {
    /*-
    Given an unsorted array of integers nums,
    return the length of the longest consecutive elements sequence.

    You must write an algorithm that runs in O(n) time.
     */
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 9, 8, 7, 6, 5, 11, 12 };

        LongestConsecutiveSequence problem = new LongestConsecutiveSequence();
        String res = "The length of the longest consecutive sequence in the array" + "\n" +
                Arrays.toString(nums) + "\n\n" +
                "Bruteforce : " + problem.solve(nums) + "\n" +
                "Optimized : " + problem.solveBetter(nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int solve(int[] nums) {
        // sort the input
        // O(n.log n)
        Arrays.sort(nums);

        // take the first element
        int currentStreak = 1, maxLength = 1, end = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                if (nums[i - 1] + 1 == nums[i]) {
                    currentStreak += 1;
                } else if (maxLength < currentStreak) {
                    maxLength = currentStreak;
                    currentStreak = 1;
                    end = i;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        int temp = maxLength;
        while (temp > 0) {
            result.add(nums[end + 1]);
            end--;
            temp--;
        }

        // System.out.println(result);
        return maxLength;
    }

    private int solveBetter(int[] nums) {
        Set<Integer> set = new HashSet<>();

        // add all numbers to a set
        Arrays.stream(nums).forEach(num -> set.add(num));
        int currentStreak = 0, maxLength = 0, end = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (!set.contains(num - 1)) {
                currentStreak = 1;

                // while we can find the next number in the set, the streak continues
                while (set.contains(num + 1)) {
                    num += 1;
                    currentStreak += 1;
                }

                if (maxLength < currentStreak) {
                    maxLength = currentStreak;
                    // end = i;
                }
            }
        }

        // List<Integer> result = new ArrayList<>();
        // int temp = maxLength;
        // while (temp > 0) {
        //     result.add(nums[end - 1]);
        //     end--;
        //     temp--;
        // }

        // System.out.println(result);
        return maxLength;
    }
}
