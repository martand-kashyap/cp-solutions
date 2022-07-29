package search.binary;

import java.io.PrintWriter;

class CountOfElementsInSortedArray {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 2, 2, 3,};
        int searchKey = 2, n = arr.length;

        CountOfElementsInSortedArray countElements = new CountOfElementsInSortedArray();

        String res = "Element : " + searchKey + "\noccurs " + countElements.countOfElement(arr, n, searchKey) + " times";

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int countOfElement(int[] arr, int n, int searchKey) {
        FirstAndLastOccurrence flo = new FirstAndLastOccurrence();

        int f = flo.firstOccurrence(arr, searchKey);
        int l = flo.lastOccurrence(arr, searchKey);

        return l - f + 1;
    }
}
