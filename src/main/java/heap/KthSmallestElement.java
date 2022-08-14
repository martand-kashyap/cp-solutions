package heap;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class KthSmallestElement {
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int n = arr.length, k =4 ;

        KthSmallestElement kse = new KthSmallestElement();
        String res = "kth smallest element (k=" + k + ") in " + Arrays.toString(arr) + " is: -\n\n"
                + "Using sorting [O(n.log n)] : " + kse.usingSorting(arr, n, k) + "\n"
                + "Using Min Heap (size = " + n + ") [O(n + k.log n)] : " + kse.usingMinHeap(arr, n, k) + "\n"
                + "Using Max Heap (size = " + k + ") [O(n.log k)] : " + kse.usingMaxHeap(arr, n, k);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int usingSorting(int[] arr, int n, int k) {
        int[] temp = Arrays.copyOf(arr, n);
        Arrays.sort(temp);
        return temp[k - 1];
    }

    private int usingMinHeap(int[] arr, int n, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>(n);

        for (int i = 0; i < n; i++) {
            minHeap.offer(arr[i]);
        }

        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }

        return minHeap.peek();
    }

    private int usingMaxHeap(int[] arr, int n, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            maxHeap.offer(arr[i]);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.peek();
    }
}
