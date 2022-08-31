package search.binary;

import java.io.PrintWriter;

class OrderAgnosticSearch {
    public static void main(String[] args) {
        int[] arrDec = {40, 10, 5, 2, 1};
        int[] arrAsc = {1, 2, 5, 10, 40};
        int searchKey = 10, n = arrDec.length;

        OrderAgnosticSearch oas = new OrderAgnosticSearch();
        String res =
                "Order Agnostic Binary Search (asc order) : " + oas.orderAgnosticBinarySearch(arrAsc, n, searchKey) + "\n"
                        + "Order Agnostic Binary Search (dsc order) : " + oas.orderAgnosticBinarySearch(arrDec, n, searchKey);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int orderAgnosticBinarySearch(int[] arr, int n, int searchKey) {
        if (arr[0] < arr[1])
            return binarySearchAsc(arr, 0, n - 1, searchKey);
        else
            return binarySearchDsc(arr, 0, n - 1, searchKey);
    }

    int binarySearchAsc(int[] arr, int l, int u, int searchKey) {
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

    int binarySearchDsc(int[] arr, int l, int u, int searchKey) {
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (searchKey < arr[m])
                l = m + 1;
            else if (searchKey > arr[m])
                u = m - 1;
            else
                return m;
        }
        return -1;
    }
}
