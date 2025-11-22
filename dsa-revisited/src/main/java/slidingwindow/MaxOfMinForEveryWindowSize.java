package slidingwindow;

import java.util.Arrays;

class MaxOfMinForEveryWindowSize {
    /*-
    Given an integer array arr[] of size N,
    find the maximum of the minimums for every window size in the given array.

    Note: The window size varies from 1 to N.

    Input: arr[] = {4, 1, 2, 4, 5, 1, 2, 4}
    Output: 5 4 2 1 1 1 1 1

    Explanation

    Window Size :
    1 => windows { (4), (1), (2), (4), (5), (1), (2), (4) }
      => minimum = {4, 1, 2, 4, 5, 1, 2, 4}
      => maximum of minimums = 5
    2 => windows { (4, 1), (1, 2), (2, 4), (4, 5), (5, 1), (1, 2), (2, 4) }
      => minimum = {1, 1, 2, 4, 1, 1, 2}
      => maximum of minimums = 4
    3 => windows { (4, 1, 2), (1, 2, 4), (2, 4, 5), (4, 5, 1), (5, 1, 2), (1, 2, 4) }
      => minimum = {1, 1, 2, 1, 1, 1}
      => maximum of minimums = 2
    4 => windows { (4, 1, 2, 4), (1, 2, 4, 5), (2, 4, 5, 1), (4, 5, 1, 2), (5, 1, 2, 4) }
      => minimum = {1, 1, 1, 1, 1}
      => maximum of minimums = 1
    5 => windows { (4, 1, 2, 4, 5), (1, 2, 4, 5, 1), (2, 4, 5, 1, 2), (4, 5, 1, 2, 4) }
      => minimum = {1, 1, 1, 1}
      => maximum of minimums = 1
    6 => windows { (4, 1, 2, 4, 5, 1), (1, 2, 4, 5, 1, 2), (2, 4, 5, 1, 2, 4) }
      => minimum = {1, 1, 1}
      => maximum of minimums = 1
    7 => windows { (4, 1, 2, 4, 5, 1, 2), (1, 2, 4, 5, 1, 2, 4) }
      => minimum = {1, 1}
      => maximum of minimums = 1
    7 => windows { (4, 1, 2, 4, 5, 1, 2, 4) }
      => minimum = {1}
      => maximum of minimums = 1
     */
    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 4, 5, 1, 2, 4};

        MaxOfMinForEveryWindowSize problem = new MaxOfMinForEveryWindowSize();
        String res = Arrays.toString(problem.bruteforce(nums)) + "\n"
                + Arrays.toString(problem.slidingWindowStack(nums));
    }

    private int[] bruteforce(int[] nums) {
        return new int[0];
    }

    private int[] slidingWindowStack(int[] nums) {
        return new int[0];
    }
}
