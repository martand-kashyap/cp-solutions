package search.binary;

import java.io.PrintWriter;

class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {0, 20, 30, 40, 60, 67, 99};
        int searchKey = 20, n = arr.length;

        BinarySearch bs = new BinarySearch();
        String res =
                "Binary Search (iterative): " + bs.binarySearchI(arr, n, searchKey) + "\n"
                        + "Binary Search (recursive): " + bs.binarySearchR(arr, n, searchKey);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int binarySearchR(int[] arr, int n, int searchKey) {
        return searchRecursively(arr, 0, n - 1, searchKey);
    }

    private int searchRecursively(int[] arr, int l, int u, int searchKey) {
        if (l <= u) {
            int m = l + (u - l) / 2;
            if (searchKey < arr[m])
                return searchRecursively(arr, l, m - 1, searchKey);
            else if (searchKey > arr[m])
                return searchRecursively(arr, m + 1, u, searchKey);
            else
                return m;
        }
        //if control reaches here => element not found
        return -1;
    }

    public int binarySearchI(int[] arr, int n, int searchKey) {
        int l = 0, u = n - 1;

        while (l <= u) {
            int m = l + (u - l) / 2;
            if (searchKey < arr[m])
                u = m - 1;
            else if (searchKey > arr[m])
                l = m + 1;
            else
                return m;
        }

        //if control reaches here => element not found
        return -1;
    }
}
