package heap.topkelements;

import java.io.PrintWriter;
import java.util.Arrays;

class SumBetweenK1AndK2Smallest {
    /*-
        Given an array of N positive integers and two positive integers K1 and K2,
        find the sum of all elements between K1th and K2th smallest elements of the array.
    
        It may be assumed that (1 <= k1 < k2 <= n).
     */
    public static void main(String[] args) {
        int[] arr = { 20, 8, 22, 4, 12, 10, 14 };
        int n = arr.length, k1 = 3, k2 = 6;

        SumBetweenK1AndK2Smallest k1k2 = new SumBetweenK1AndK2Smallest();
        String res = "Sum of elements bounded by [" + k1 + ", " + k2 + "]th smallest elements in \n"
                + Arrays.toString(arr) + " is \n" + k1k2.sumBetweenK1AndK2(arr, n, k1, k2);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int sumBetweenK1AndK2(int[] arr, int n, int k1, int k2) {
        KthSmallestElement kse = new KthSmallestElement();
        int l = kse.usingMaxHeap(arr, n, k1);
        int r = kse.usingMaxHeap(arr, n, k2);
        int sum = 0;

        for (int ele : arr) {
            if (l < ele && ele < r) {
                sum += ele;
            }
        }

        return sum;
    }
}
