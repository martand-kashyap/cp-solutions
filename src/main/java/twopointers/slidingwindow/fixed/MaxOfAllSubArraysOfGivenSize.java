package twopointers.slidingwindow.fixed;

import java.io.PrintWriter;
import java.util.*;

class MaxOfAllSubArraysOfGivenSize {
    /*-
    Given an array and an integer K, find the maximum for each and every contiguous sub-array of size K.

    Example:
        Input: arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, K = 3
        Output: 3 3 4 5 5 5 6
        Explanation:
           Maximum of 1, 2, 3 is 3
           Maximum of 2, 3, 1 is 3
           Maximum of 3, 1, 4 is 4
           Maximum of 1, 4, 5 is 5
           Maximum of 4, 5, 2 is 5
           Maximum of 5, 2, 3 is 5
           Maximum of 2, 3, 6 is 6
     */

    public static void main(String[] args) {
        int[] nums = {-7, -8, 7, 5, 7, 1, 6, 0};
//        [1, 3, 1, 2, 0, 5]
//        3
//        [-7,-8,7,5,7,1,6,0]
//        4
        int k = 4;

        MaxOfAllSubArraysOfGivenSize mask = new MaxOfAllSubArraysOfGivenSize();

        String res =
                "The maximum for each and every contiguous sub-array of size " + k + "\n" +
                        "in the given array\n" + Arrays.toString(nums) + "\n\n" +
                        "Bruteforce T(n) = O(n*k) : " + Arrays.toString(mask.getMaxSubArrayOfSizeKB(nums, k)) + "\n" +
                        "Sliding Window + Max Heap T(n) =  O(n*log k) : " + Arrays.toString(mask.getMaxSubArrayOfSizeSWHeap(nums, k)) + "\n" +
                        "Sliding Window + Max Heap T(n) =  O(n*log k) [Alternative Implementation]: " + Arrays.toString(mask.getMaxSubArrayOfSizeSWHeapAlternativeImpl(nums, k)) + "\n" +
                        "Sliding Window + Monotonic DeQueue T(n) = O(n) : " + Arrays.toString(mask.getMaxSubArrayOfSizeSWDeQueue(nums, k));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] getMaxSubArrayOfSizeKB(int[] nums, int k) {
        int n = nums.length, r = n - k + 1, rPtr = 0;
        int[] result = new int[r];

        for (int start = 0; start < n - k + 1; start++) {
            int localMax = nums[start];
            for (int end = start; end < start + k; end++) {
                localMax = Integer.max(localMax, nums[end]);

                if (end - start + 1 == k)
                    result[rPtr++] = localMax;
            }
        }

        return result;
    }

    private int[] getMaxSubArrayOfSizeSWHeap(int[] nums, int k) {
        int n = nums.length, r = n - k + 1, rPtr = 0, start = 0, end = 0;
        int[] result = new int[r];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        while (end < n) {
            maxHeap.offer(nums[end]);

            int currentSWSize = end - start + 1;
            if (currentSWSize < k)
                end += 1;
            else if (currentSWSize == k) {
                result[rPtr++] = maxHeap.peek();

                maxHeap.remove(nums[start]);

                start += 1;
                end += 1;
            }
        }

        return result;
    }

    private int[] getMaxSubArrayOfSizeSWHeapAlternativeImpl(int[] nums, int k) {
        int n = nums.length, r = n - k + 1, rPtr = 0, start = 0, end = 0;
        int[] result = new int[r];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (; end < k; end += 1)
            maxHeap.offer(nums[end]);

        result[rPtr++] = maxHeap.peek();

        for (; end < n; end += 1) {
            maxHeap.remove(nums[start]);
            start += 1;

            maxHeap.offer(nums[end]);

            result[rPtr++] = maxHeap.peek();
        }

        return result;
    }

    private int[] getMaxSubArrayOfSizeSWDeQueue(int[] nums, int k) {
        int n = nums.length, r = n - k + 1, rPtr = 0, start = 0, end = 0;
        int[] result = new int[r];

        //Used with non-increasing(decreasing) monotonicity
        Deque<Integer> q = new ArrayDeque<>();

        while (end < n) {
            //pre-process the right boundary element
            //by preserving the monotonicity of the queue
            while (!q.isEmpty() && q.peekLast() < nums[end]) {
                q.pollLast();
            }
            //process the right boundary
            q.offerLast(nums[end]);

            int currentSWSize = end - start + 1;
            if (currentSWSize < k)
                end += 1;
            else if (currentSWSize == k) {
                //get the answer
                result[rPtr++] = q.peekFirst();

                //process the left boundary
                if (!q.isEmpty() && q.peekFirst() == nums[start])
                    q.pollFirst();

                //slide the window forward
                start += 1;
                end += 1;
            }
        }

        return result;
    }
}
