package search.binary;

import java.io.PrintWriter;

class SortedRotatedArraySearch {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int searchKey = 1;

        SortedRotatedArraySearch problem = new SortedRotatedArraySearch();
        String res = "Search Key found at index " + problem.solveInTwoPass(arr, searchKey);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int bruteforce(int[] nums, int searchKey) {
        for (int curr : nums) {
            if (curr == searchKey) {
                return curr;
            }
        }
        //T(n) = O(n)
        return -1;
    }

    private int solveInTwoPass(int[] nums, int searchKey) {
        int p = findPivotIndex(nums, nums.length);

        int idx1 = binarySearchI(nums, 0, p - 1, searchKey);
        int idx2 = binarySearchI(nums, p, nums.length - 1, searchKey);

        if (idx1 == -1 && idx2 == -1)
            return -1;
        else
            return idx1 == -1 ? idx2 : idx1;
    }

    private int findPivotIndex(int[] nums, int n) {
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

        return left;
    }

    private int binarySearchI(int[] arr, int l, int u, int searchKey) {
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

    private int solveInOnePass(int[] nums, int searchKey) {
        int n = nums.length, left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == searchKey) {
                return mid;
            }
            //is the left half sorted?
            else if (nums[left] <= nums[mid]) {
                //left half is indeed sorted
                //is the target within the sorted bounds?
                if (nums[left] <= searchKey && searchKey < nums[mid]) {
                    //yes
                    right = mid - 1;
                } else {
                    //no
                    left = mid + 1;
                }
            } else {
                //left half is not sorted => right half MUST be sorted
                //is the target within the sorted bounds?
                if (nums[mid] < searchKey && searchKey <= nums[right]) {
                    //yes
                    left = mid + 1;
                } else {
                    //no
                    right = mid - 1;
                }
            }
        }
        return -1;
        //T(n) = O(log n)
    }
}
