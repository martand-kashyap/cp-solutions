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
            return binarySearchAsc(arr, n, searchKey);
        else
            return binarySearchDsc(arr, n, searchKey);
    }

    private int binarySearchAsc(int[] arr, int n, int searchKey) {
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
        return -1;
    }

    private int binarySearchDsc(int[] arr, int n, int searchKey) {
        int l = 0, u = n - 1;
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
