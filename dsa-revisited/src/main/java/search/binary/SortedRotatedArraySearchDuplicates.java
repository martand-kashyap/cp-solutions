package search.binary;

import java.io.PrintWriter;

class SortedRotatedArraySearchDuplicates {
    public static void main(String[] args) {
        int[] arr = {2,5,6,0,0,1,2};
        int searchKey = 0;

        SortedRotatedArraySearchDuplicates problem = new SortedRotatedArraySearchDuplicates();
        String res = "Search Key found at index " + problem.solve(arr, searchKey);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int solve(int[] nums, int searchKey) {
        int n = nums.length, left = 0, right = n - 1;

        while (left <= right) {
            //move left pointer to avoid duplicates
            while (left < right && nums[left] == nums[left + 1]) {
                left += 1;
            }
            //move right pointer to avoid duplicates
            while (left < right && nums[right - 1] == nums[right]) {
                right -= 1;
            }

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
