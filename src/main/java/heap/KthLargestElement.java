package heap;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class KthLargestElement {
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int n = arr.length, k = 4;

        KthLargestElement kse = new KthLargestElement();
        String res = "kth largest element (k=" + k + ") in " + Arrays.toString(arr) + " is: -\n\n"
                + "Using sorting [O(n.log n)] : " + kse.usingSorting(arr, n, k) + "\n"
                + "Using Max Heap (size = " + n + ") [O(n + k.log n)] : " + kse.usingMaxHeap(arr, n, k) + "\n"
                + "Using Min Heap (size = " + k + ") [O(n.log k)] : " + kse.usingMinHeap(arr, n, k);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int usingSorting(int[] arr, int n, int k) {
        int[] temp = Arrays.copyOf(arr, n);
        Arrays.sort(temp);
        return temp[n - k];
    }

    private int usingMaxHeap(int[] arr, int n, int k) {
        Queue<Integer> maxHeap = new PriorityQueue<>(n, Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            maxHeap.offer(arr[i]);
        }

        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }

        return maxHeap.peek();
    }

    private int usingMinHeap(int[] arr, int n, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int i = 0; i < n; i++) {
            minHeap.offer(arr[i]);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
