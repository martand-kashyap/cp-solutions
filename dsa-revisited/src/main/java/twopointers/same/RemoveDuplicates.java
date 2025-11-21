package twopointers.same;

import java.io.PrintWriter;
import java.util.Arrays;

class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 4, 4, 4, 5, 5};

        RemoveDuplicates problem = new RemoveDuplicates();
        String res = "Array size after removing duplicates from" + "\n" +
                Arrays.toString(nums) + "\n\n" +
                "2 pointers : " + problem.solve2P(nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    int solve2P(int[] nums) {
        int left = 0, right = 1, n = nums.length;

        if (n <= 1)
            return n;

        while (right < n) {
            if (nums[left] == nums[right])
                right++;
            else {
                left++;
                nums[left] = nums[right];
            }
        }

        return left + 1;
    }
}
