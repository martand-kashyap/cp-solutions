package search.binary;

import java.io.PrintWriter;

class RotatedArraySearch {
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int searchKey = 3, n = arr.length;

        RotatedArraySearch obj = new RotatedArraySearch();
        String res = "Search Key found at index " + obj.searchInRotatedSortedArray(arr, n, searchKey);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int searchInRotatedSortedArray(int[] arr, int n, int searchKey) {
        //find pivot
        RotatedArrayFindPivot findPivot = new RotatedArrayFindPivot();
        int p = findPivot.findPivotIndex(arr, n);

        //search in both halves of the array
        BinarySearch bs = new BinarySearch();
        int idx1 = bs.searchRecursively(arr, 0, p, searchKey);
        int idx2 = bs.searchRecursively(arr, p + 1, n - 1, searchKey);

        if (idx1 == -1 && idx2 == -1)
            return -1;
        else
            return idx1 == -1 ? idx2 : idx1;
    }
}
