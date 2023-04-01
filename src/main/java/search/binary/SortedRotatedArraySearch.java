package search.binary;

import java.io.PrintWriter;

class SortedRotatedArraySearch {
    public static void main(String[] args) {
        int[] arr = {3, 1};
        int searchKey = 1;

        SortedRotatedArraySearch obj = new SortedRotatedArraySearch();
        String res = "Search Key found at index " + obj.searchInRotatedSortedArray(arr, searchKey);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int searchInRotatedSortedArray(int[] nums, int searchKey) {
        int p = findPivotIndex(nums, nums.length);

        int idx1 = orderAgnosticBinarySearch(nums, 0, p, searchKey);
        int idx2 = orderAgnosticBinarySearch(nums, p + 1, nums.length - 1, searchKey);

        if (idx1 == -1 && idx2 == -1)
            return -1;
        else
            return idx1 == -1 ? idx2 : idx1;
    }

    private int findPivotIndex(int[] nums, int n) {
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
            } else if (nums[left] <= nums[midIdx]) {
                left = midIdx + 1;
            }
        }

        return -1;
    }

    private int orderAgnosticBinarySearch(int[] arr, int l, int r, int searchKey) {
        if (arr[0] < arr[1])
            return binarySearchAsc(arr, l, r, searchKey);
        else
            return binarySearchDsc(arr, l, r, searchKey);
    }

    private int binarySearchAsc(int[] arr, int l, int u, int searchKey) {
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (searchKey < arr[m])
                u = m - 1;
            else if (searchKey > arr[m])
                l = m + 1;
            else
                return m;
        }
        return -1;
    }

    private int binarySearchDsc(int[] arr, int l, int u, int searchKey) {
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (searchKey < arr[m])
                l = m + 1;
            else if (searchKey > arr[m])
                u = m - 1;
            else
                return m;
        }
        return -1;
    }
}
