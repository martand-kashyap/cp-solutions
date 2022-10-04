package twopointers.slidingwindow.fixed;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class FirstNegativeInEveryWindowOfGivenSize {
    public static void main(String[] args) {
        int[] nums = {12, -1, -7, 8, -15, 30, 16, 28};
        int k = 3;

        FirstNegativeInEveryWindowOfGivenSize fnnwk = new FirstNegativeInEveryWindowOfGivenSize();

        String res = "First negative integer in every window of size " + k + "\n" +
                "in the given array\n" + Arrays.toString(nums) + "\n\n" +
                "Bruteforce T(n) = O(n*k) : " + Arrays.toString(fnnwk.solveB(nums, k)) + "\n" +
                "Sliding Window T(n) = O(n) : " + Arrays.toString(fnnwk.solveSW(nums, k));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] solveB(int[] nums, int k) {
        int n = nums.length, resultSize = n - k + 1, rPtr = 0;
        int[] result = new int[resultSize];

        for (int i = 0; i < n - k; i++) {
            boolean isFirstNegativeFound = false;

            for (int j = i; j < i + k; j++) {
                if (nums[j] < 0) {
                    result[rPtr++] = nums[j];
                    isFirstNegativeFound = true;
                    break;
                }
            }

            if (!isFirstNegativeFound)
                result[rPtr++] = 0;
        }

        return result;
    }

    private int[] solveSW(int[] nums, int k) {
        int n = nums.length, rPtr = 0, left = 0, right = 0;
        //the size of the result list
        int resultSize = n - k + 1;
        int[] result = new int[resultSize];

        //Queue to keep track of the negative elements in the current SW
        Queue<Integer> q = new LinkedList<>();

        while (right < n) {
            //Process the current element
            if (nums[right] < 0)
                q.offer(nums[right]);

            int currentSWSize = right - left + 1;
            if (currentSWSize < k)
                right += 1;
            else if (currentSWSize == k) {
                if (q.isEmpty())
                    result[rPtr++] = 0;
                else
                    result[rPtr++] = q.peek();

                //Process the element leaving the current SW
                if (!q.isEmpty() && nums[left] == q.peek())
                    q.poll();

                //move the current SW forward
                left += 1;
                right += 1;
            }
        }

        return result;
    }
}
