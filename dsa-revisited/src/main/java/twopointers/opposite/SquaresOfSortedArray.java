package twopointers.opposite;

import java.util.Arrays;

class SquaresOfSortedArray {
    /*-
    Given an integer array nums sorted in non-decreasing order,
    return an array of the squares of each number sorted in non-decreasing order.

    Example 1:

    Input: nums = [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
    Explanation: After squaring, the array becomes [16,1,0,9,100].
    After sorting, it becomes [0,1,9,16,100].

    Example 2:

    Input: nums = [-7,-3,2,3,11]
    Output: [4,9,9,49,121]
     */
    public static void main(String[] args) {
        int[] nums = {-7, -3, 2, 3, 11};
        int[] result = new int[nums.length];

        int l = 0, r = nums.length - 1, k = nums.length - 1;
        while (l <= r) {
            if (Math.abs(nums[l]) < Math.abs(nums[r])) {
                result[k--] = nums[r] * nums[r];
                r--;
            } else {
                result[k--] = nums[l] * nums[l];
                l++;
            }
        }

        System.out.println(Arrays.toString(result));
    }
}
