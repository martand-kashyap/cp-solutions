package heap;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class ConnectRopesMinCost {

    /*-
    There are given n ropes of different lengths, we need to connect these ropes into one rope.
    The cost to connect two ropes is equal to the sum of their lengths. We need to connect the ropes with minimum cost.
    
    For example, if we are given 4 ropes of lengths 4, 3, 2, and 6. We can connect the ropes in the following ways.
    First, connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6, and 5.
    Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
    Finally connect the two ropes and all ropes have connected.
    Total cost for connecting all ropes is 5 + 9 + 15 = 29.
    This is the optimized cost for connecting ropes.
     */
    public static void main(String[] args) {
        int[] lengths = { 4, 3, 2, 6 };

        ConnectRopesMinCost crmc = new ConnectRopesMinCost();
        String res = "Minimized cost for connecting ropes: " + crmc.findLeastCost(lengths);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int findLeastCost(int[] lengths) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int minCost = 0;

        // build the minHeap
        List<Integer> temp = Arrays.stream(lengths).boxed().toList();
        minHeap.addAll(temp);

        while (minHeap.size() > 1) {
            int cost1 = minHeap.poll();
            int cost2 = minHeap.poll();

            minHeap.offer(cost1 + cost2);
            minCost += cost1 + cost2;
        }

        return minCost;
    }
}
