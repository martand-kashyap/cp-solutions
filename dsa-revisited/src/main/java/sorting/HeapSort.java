package sorting;

import java.io.PrintWriter;
import java.util.Arrays;

class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        int n = arr.length;

        HeapSort sort = new HeapSort();
        StringBuffer res = new StringBuffer();
        res.append("The array : \n").append(Arrays.toString(arr));
        sort.heapSort(arr, n);
        res.append("\nSorted Array\n").append(Arrays.toString(arr));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    //Function to sort an array using Heap Sort.
    public void heapSort(int[] arr, int n) {
        buildHeap(arr, n);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    void buildHeap(int[] arr, int n) {
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    //Heapify function to maintain heap property.
    void heapify(int[] arr, int n, int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int maxIndex = i;

        if (l < n && arr[l] > arr[i])
            maxIndex = l;

        if (r < n && arr[r] > arr[maxIndex])
            maxIndex = r;

        if (maxIndex != i) {
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;

            heapify(arr, n, maxIndex);
        }
    }
}
