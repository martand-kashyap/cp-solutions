package twopointers.same;

import java.io.PrintWriter;
import java.util.Arrays;

class Merge2SortedArrays {
    /*-
        Given two sorted arrays, the task is to merge them in a sorted manner.

        Input: arr1[] = { 1, 3, 4, 5}, arr2[] = {2, 4, 6, 8}
        Output: arr3[] = {1, 2, 3, 4, 4, 5, 6, 8}

        Input: arr1[] = { 5, 8, 9}, arr2[] = {4, 7, 8}
        Output: arr3[] = {4, 5, 7, 8, 8, 9}
     */
    public static void main(String[] args) {
        int nums1[] = {5, 8, 9}, nums2[] = {4, 7, 8};

        Merge2SortedArrays problem = new Merge2SortedArrays();

        String res = "The sorted arrays" + "\n" +
                Arrays.toString(nums1) + "\n" +
                Arrays.toString(nums2) + "\n" +
                "can be merged as" + "\n" +
                Arrays.toString(problem.solve2P(nums1, nums2));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] solve2P(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i];
                i++;
            } else if (nums1[i] > nums2[j]) {
                merged[k++] = nums2[j];
                j++;
            } else {
                merged[k++] = nums1[i];
                merged[k++] = nums2[j];
                i++;
                j++;
            }
        }

        while (i < m) {
            merged[k++] = nums1[i];
            i++;
        }

        while (j < n) {
            merged[k++] = nums2[j];
            j++;
        }

        return merged;
    }
}
