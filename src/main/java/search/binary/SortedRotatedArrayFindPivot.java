package search.binary;

import java.io.PrintWriter;

class SortedRotatedArrayFindPivot {
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int n = arr.length;

        SortedRotatedArrayFindPivot findPivot = new SortedRotatedArrayFindPivot();
        String res = "The array is rotated at element " + findPivot.findPivotElementAltImpl2(arr, n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int findPivotElement(int[] nums, int n) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int l = 0, r = n - 1;

        // If the first element is less than the last element then there is no rotation. The first element is minimum.
        if (nums[l] <= nums[r]) {
            return nums[l];
        }

        while (l <= r) {
            int m = (l + r) / 2;

            // If the middle element is smaller than its previous element, then it is the minimum element
            if (m > 0 && nums[m] < nums[m - 1]) {
                return nums[m];
            }

            // If the middle is greater than its next element, then the next element is the minimum element
            if (m < n - 1 && nums[m] > nums[m + 1]) {
                return nums[m + 1];
            }

            if (nums[l] <= nums[m]) { // left array is sorted. So the pivot is on the right side
                l = m + 1;
            } else { //right array is sorted. So the pivot is on the left side
                r = m - 1;
            }
        }
        return -1;
    }

    /*
    //fails some scenarios
    private int findPivotElementAltImpl1(int[] nums, int n) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int midIdx = left + (right - left) / 2;
            int prevIdx = (midIdx - 1 + n) % n;
            int nextIdx = (midIdx + 1) % n;

            if (nums[prevIdx] >= nums[midIdx] && nums[midIdx] <= nums[nextIdx]) {
                return nums[midIdx];
            }
            //it is important to check if the right half is sorted or not before checking the left half
            else if (nums[midIdx] <= nums[right]) {
                right = midIdx - 1;
            } else if (nums[left] <= nums[midIdx]) {
                left = midIdx + 1;
            }
        }

        return -1;
    }*/

    private int findPivotElementAltImpl2(int[] nums, int n) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = n - 1;

        while (left < right) {
            //Case 1: The leftmost value is less than the rightmost value in the array. This means that the array is not rotated.
            //Example: [1 2 3 4 5 6 7 8 9]
            if (nums[left] < nums[right]) {
                return nums[left];
            }

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                //Case 2: The value in the middle of the array is greater than the leftmost and rightmost values in the array.
                //Example: [4 5 6 7 |8| 9 1 2 3]
                left = mid + 1;
            } else {
                //Case 3: The value in the middle of the array is less than the leftmost and rightmost values in the array.
                //Example: [7 8 9 1 |2| 3 4 5 6]
                right = mid;
            }
        }

        return nums[left];
    }
}
