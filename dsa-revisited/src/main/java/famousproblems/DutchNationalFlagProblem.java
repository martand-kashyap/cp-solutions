package famousproblems;

import java.io.PrintWriter;
import java.util.Arrays;

class DutchNationalFlagProblem {
    /*-
    Given an array nums with n objects colored red, white, or blue,
    sort them in-place so that objects of the same color are adjacent,
    with the colors in the order red, white, and blue.

    We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

    You must solve this problem without using the library's sort function.

    Example 1:

    Input: nums = [2,0,2,1,1,0]
    Output: [0,0,1,1,2,2]

    Example 2:

    Input: nums = [2,0,1]
    Output: [0,1,2]
     */
    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};

        DutchNationalFlagProblem p = new DutchNationalFlagProblem();

        String res = "The input array \n" + Arrays.toString(nums) + "\n" +
                "after sorting in place results in" + "\n" +
                "using counts: " + Arrays.toString(p.usingCounts(nums)) + "\n" +
                "using pointers: " + Arrays.toString(p.using2Pointers(nums));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] usingCounts(int[] nums) {
        int zeroCount = 0, oneCount = 0, twoCount = 0;

        for (int x : nums) {
            if (x == 0) {
                zeroCount++;
            } else if (x == 1) {
                oneCount++;
            } else {
                twoCount++;
            }
        }

        int i = 0;
        while (zeroCount > 0) {
            nums[i++] = 0;
            zeroCount--;
        }
        while (oneCount > 0) {
            nums[i++] = 1;
            oneCount--;
        }
        while (twoCount > 0) {
            nums[i++] = 2;
            twoCount--;
        }

        return nums;
    }

    private int[] using2Pointers(int[] nums) {
        int left = 0, right = nums.length - 1, current = 0, temp;

        while (current <= right) {
            switch (nums[current]) {
                case 0:
                    temp = nums[left];
                    nums[left] = nums[current];
                    nums[current] = temp;

                    left += 1;
                    current += 1;

                    break;
                case 1:
                    current += 1;
                    break;
                case 2:
                    temp = nums[right];
                    nums[right] = nums[current];
                    nums[current] = temp;

                    right -= 1;

                    break;
            }
        }

        return nums;
    }
}
