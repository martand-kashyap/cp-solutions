package heap;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

class SortKSortedArray {
    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int n = arr.length, k = 3;

        SortKSortedArray kse = new SortKSortedArray();
        int[] result = kse.sortNearlySorted(arr, n, k);
        String res = "k-sorted list (k=" + k + ") " + Arrays.toString(arr) + " after sorting: -\n\n"
                + "Using Min Heap (size = " + k + ") [O(n.log k)] : \n" + Arrays.toString(result);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] sortNearlySorted(int[] arr, int num, int k) {
        int[] result = new int[num];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        int i = 0;

        for (int ele : arr) {
            minHeap.offer(ele);

            if (minHeap.size() > k) {
                result[i++] = minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll();
        }

        return result;
    }
}
