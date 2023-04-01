package search.binary;

import java.io.PrintWriter;

class SortedRotatedArrayFindPivot {
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int n = arr.length;

        SortedRotatedArrayFindPivot findPivot = new SortedRotatedArrayFindPivot();
        String res = "The array is rotated at index " + findPivot.findPivotIndex(arr, n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    int findPivotIndex(int[] nums, int n) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int midIdx = left + (right - left) / 2;
            int prevIdx = (midIdx - 1 + n) % n;
            int nextIdx = (midIdx + 1) % n;

            if (nums[prevIdx] >= nums[midIdx] && nums[midIdx] <= nums[nextIdx]) {
                return midIdx;
            }
            //it is important to check if the right half is sorted or not before checking the left half
            else if (nums[midIdx] <= nums[right]) {
                right = midIdx - 1;
            }
            else if (nums[left] <= nums[midIdx]) {
                left = midIdx + 1;
            }
        }

        return -1;
    }
}
