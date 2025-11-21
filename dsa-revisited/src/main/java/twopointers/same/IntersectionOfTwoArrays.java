package twopointers.same;

import java.io.PrintWriter;
import java.util.*;

class IntersectionOfTwoArrays {
    /*-
    Given two integer arrays nums1 and nums2, return an array of their intersection.
    Each element in the result must be unique and you may return the result in any order.

    Example 1:
        Input: nums1 = [1,2,2,1], nums2 = [2,2]
        Output: [2]

    Example 2:
        Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        Output: [9,4]
        Explanation: [4,9] is also accepted.
     */
    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5}, nums2 = {9, 4, 9, 8, 4};

        IntersectionOfTwoArrays problem = new IntersectionOfTwoArrays();

        String res = "Intersection of the Arrays" + "\n" +
                Arrays.toString(nums1) + " and \n" +
                Arrays.toString(nums2) + " is \n\n" +
                "Set : " + Arrays.toString(problem.solveSet(nums1, nums2)) + "\n" +
                "2 pointers : " + Arrays.toString(problem.solve2PUnsortedArrays(nums1, nums2));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] solveSet(int[] nums1, int[] nums2) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();

        for (int x : nums1) {
            seen.add(x);
        }

        for (int x : nums2) {
            if (seen.contains(x)) {
                intersection.add(x);
            }
        }

        return intersection.stream().mapToInt(Number::intValue).toArray();
    }

    private int[] solve2PUnsortedArrays(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i += 1;
            } else if (nums1[i] > nums2[j]) {
                j += 1;
            } else {
                if (result.size() == 0 || result.get(result.size() - 1) != nums1[i])
                    result.add(nums1[i]);
                i += 1;
                j += 1;
            }
        }

        return result.stream().mapToInt(Number::intValue).toArray();
    }
}
