package twopointers.same;


import java.io.PrintWriter;
import java.util.*;

class UnionOfTwoArrays {
    /*-
        Given two arrays a[] and b[] of size n and m respectively.
        The task is to find the number of elements in the union between these two arrays.

        Union of the two arrays can be defined as the set containing distinct elements from both the arrays.
        If there are repetitions, then only one occurrence of element should be printed in the union.

        Note : Elements are not necessarily distinct.

        Example:
        6 2
        85 25 1 32 54 6
        85 2
    */
    public static void main(String[] args) {
        int[] nums1 = {85, 25, 1, 32, 54, 6};
        int[] nums2 = {85, 2};

        UnionOfTwoArrays problem = new UnionOfTwoArrays();
        String res = "Union of the Arrays" + "\n" +
                Arrays.toString(nums1) + " and \n" +
                Arrays.toString(nums2) + " is \n\n" +
                "Set : " + Arrays.toString(problem.solveSet(nums1, nums2)) + "\n" +
                "Set (Optimized) : " + Arrays.toString(problem.solveSetOptimized(nums1, nums2)) + "\n" +
                "Map : " + Arrays.toString(problem.solveMap(nums1, nums2)) + "\n" +
                "2 pointers : " + Arrays.toString(problem.solve2PUnsortedArrays(nums1, nums2));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] solveSet(int[] nums1, int[] nums2) {
        Set<Integer> unionSet = new HashSet<>();

        for (int temp : nums1) {
            unionSet.add(temp);
        }

        for (int temp : nums2) {
            unionSet.add(temp);
        }

        // Time Complexity: O(m.log m + n.log n)
        // Auxiliary Space: O(m + n)
        return unionSet.stream().mapToInt(Number::intValue).toArray();
    }

    private int[] solveSetOptimized(int[] nums1, int[] nums2) {
        Set<Integer> unionSet = new HashSet<>();

        int smallerLength = Math.min(nums1.length, nums2.length);

        for (int i = 0; i < smallerLength; i++) {
            unionSet.add(nums1[i]);
            unionSet.add(nums2[i]);
        }

        if (nums1.length < nums2.length) {
            for (int i = nums1.length; i < nums2.length; i++) {
                unionSet.add(nums2[i]);
            }
        } else {
            for (int i = nums2.length; i < nums1.length; i++) {
                unionSet.add(nums1[i]);
            }
        }

        // Time Complexity: O(max(m, n))
        // Auxiliary Space: O(max(m, n))
        return unionSet.stream().mapToInt(Number::intValue).toArray();
    }

    private int[] solveMap(int[] nums1, int[] nums2) {
        Map<Integer, Integer> numberCountMap = new HashMap<>();

        for (int temp : nums1) {
            numberCountMap.put(temp, numberCountMap.getOrDefault(temp, 0) + 1);
        }
        for (int temp : nums2) {
            numberCountMap.put(temp, numberCountMap.getOrDefault(temp, 0) + 1);
        }

        // Time Complexity: O(m * log(m) + n * log(n)), for using map data structure.
        // Auxiliary Space: O(m + n)
        return numberCountMap.keySet().stream().mapToInt(Number::intValue).toArray();
    }

    private int[] solve2PUnsortedArrays(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> unionResult = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                if (unionResult.size() == 0 || unionResult.get(unionResult.size() - 1) != nums1[i]) {
                    unionResult.add(nums1[i]);
                }
                i++;
            } else if (nums1[i] > nums2[j]) {
                if (unionResult.size() == 0 || unionResult.get(unionResult.size() - 1) != nums2[j]) {
                    unionResult.add(nums2[j]);
                }
                j++;
            } else {
                if (unionResult.size() == 0 || unionResult.get(unionResult.size() - 1) != nums2[j]) {
                    unionResult.add(nums2[j]);
                }
                i++;
                j++;
            }
        }

        // T(n) : O(max (m, n)) : if sort() is not called
        // T(n) : O(max (m.logm, n.logn)) : if sort() is called
        return unionResult.stream().mapToInt(Number::intValue).toArray();
    }
}
