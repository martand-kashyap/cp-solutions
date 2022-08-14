package heap;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

class KLargestElements {
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int n = arr.length, k = 3;

        KLargestElements kle = new KLargestElements();
        int[] result = kle.getKLargestElements(arr, n, k);
        String res = "k-largest element (k=" + k + ") in " + Arrays.toString(arr) + " are: -\n\n"
                + "Using Min Heap (size = " + k + ") [O(n.log k)] : " + Arrays.toString(result);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[] getKLargestElements(int[] arr, int n, int k) {
        int[] result = new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int i = 0; i < n; i++) {
            minHeap.offer(arr[i]);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll();
        }

        return result;
    }
}
