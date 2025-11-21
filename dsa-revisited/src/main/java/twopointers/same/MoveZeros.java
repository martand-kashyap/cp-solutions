package twopointers.same;

import java.io.PrintWriter;
import java.util.Arrays;

class MoveZeros {

    /*-
        Given an array of integers, move all the 0s to the back of the array
        while maintaining the relative order of the non-zero elements.
        Do this in-place using constant auxiliary space.

        Input: [1, 0, 2, 0, 0, 7]
        Output:[1, 2, 7, 0, 0, 0]
     */
    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 0, 0, 7};

        MoveZeros problem = new MoveZeros();

        String res = "After moving 0s to end in the input array\n" +
                Arrays.toString(nums) + "\n" +
                "the result is : " + Arrays.toString(problem.using2P(nums));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] using2P(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }

        int i = -1;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                i++;
                nums[i] = nums[j];
            }
        }

        // fill the trailing spaces with 0
        while (i < nums.length - 1) {
            i++;
            nums[i] = 0;
        }
        return nums;
        //T(n) = O(n)
        //S(n) = O(1)
    }
}
