package heap;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class KSmallestElements {
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int n = arr.length, k = 3;

        KSmallestElements kse = new KSmallestElements();
        int[] result = kse.getKSmallestElements(arr, n, k);
        String res = "k-smallest element (k=" + k + ") in " + Arrays.toString(arr) + " are: -\n\n"
                + "Using Max Heap (size = " + k + ") [O(n.log k)] : " + Arrays.toString(result);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] getKSmallestElements(int[] arr, int n, int k) {
        int[] result = new int[k];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            maxHeap.offer(arr[i]);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int i = 0;
        while (!maxHeap.isEmpty()) {
            result[i++] = maxHeap.poll();
        }

        return result;
    }
}
