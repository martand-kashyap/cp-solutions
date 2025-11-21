package median;

import java.io.PrintWriter;
import java.util.Arrays;

class Median2SortedArraysLengthEqual {
    public static void main(String[] args) {
        int arr1[] = {1, 2, 3, 6}, arr2[] = {4, 6, 8, 10};
        int m = arr1.length, n = arr2.length;

        Median2SortedArraysLengthEqual median = new Median2SortedArraysLengthEqual();
        String res =
                "Median of\n" + Arrays.toString(arr1) + "\n"
                + "and \n" + Arrays.toString(arr2) + "\n" + "is : \n"
                + median.usingBinarySearch(arr1, arr2, m, n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int usingBinarySearch(int[] arr1, int[] arr2, int m, int n) {
        if (m != n) {
            System.out.println("Does not work for unequal arrays.");
            return -1;
        }

        return -1;
    }

    private int usingHeap(int[] arr1, int[] arr2, int m, int n){
        return -1;
    }
}

class Median2SortedArraysLengthUnequal {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
