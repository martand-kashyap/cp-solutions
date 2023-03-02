package heap.topkelements;

import java.io.PrintWriter;
import java.util.Arrays;

class KClosestElements {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6};
        int n = arr.length, k = 2, x = 3;

        KClosestElements closestElements = new KClosestElements();
        int[] result = closestElements.usingBinarySearch(arr, n, k, x);

        String res = Arrays.toString(result);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] usingBinarySearch(int[] arr, int n, int k, int x) {
        int[] result = new int[k];
        int l = 0, r = n - k;

        while (l < r) {
            int m = l + (r - l) / 2;

            if (Math.abs(x - arr[m]) > arr[m + k] - x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        for (int i = 0; i < k; i += 1) {
            result[i] = arr[l + i];
        }

        return result;
    }
}
