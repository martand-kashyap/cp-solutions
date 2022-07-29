package search.binary;

import java.io.PrintWriter;

class RotatedArrayFindPivot {
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int n = arr.length;

        RotatedArrayFindPivot findPivot = new RotatedArrayFindPivot();
        String res = "The array is rotated at index " + findPivot.findPivotIndex(arr, n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int findPivotIndex(int[] arr, int n) {
        int l = 0, u = n - 1;
        while (l <= u) {
            int m = l + (u - l) / 2;

            int nr = (m + 1) % n;
            int nl = (m - 1 + n) % n;

            if (nl > arr[m] && arr[m] < nr)
                return m;
            else if (arr[l] <= arr[m])
                //the pivot will exist only in the unsorted half of the array
                l = m + 1;
            else
                u = m - 1;
        }
        return -1;
    }
}
