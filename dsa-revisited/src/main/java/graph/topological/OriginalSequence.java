package graph.topological;

import java.io.PrintWriter;
import java.util.*;

class OriginalSequence {
    /*-
    Given a sequence originalSeq and an array of sequences,
    write a method to find if originalSeq can be uniquely reconstructed from the array of sequences.

    Unique reconstruction means that we need to find if originalSeq is the only sequence
    such that all sequences in the array are subsequences of it.

    Examples
        Input: originalSeq: [1, 2, 3, 4], seqs: [[1, 2], [2, 3], [3, 4]]
        Output: true
        Explanation: The sequences [1, 2], [2, 3], and [3, 4] can uniquely reconstruct
        [1, 2, 3, 4], in other words, all the given sequences uniquely define the order of numbers
        in the 'originalSeq'.

        Input: originalSeq: [3, 1, 4, 2, 5], seqs: [[3, 1, 5], [1, 4, 2, 5]]
        Output: true
        Explanation: The sequences [3, 1, 5] and [1, 4, 2, 5] can uniquely reconstruct
        [3, 1, 4, 2, 5].
     */
    public static void main(String[] args) {
        int[] originalSequence = {1, 2, 3, 4};
        int[][] sequences = {{1, 2}, {2, 3}, {3, 4}};

        StringBuilder sequenceRep = new StringBuilder();
        for (int[] r : sequences) {
            sequenceRep.append(Arrays.toString(r)).append("\n");
        }

        OriginalSequence problem = new OriginalSequence();
        String res = "The original sequence : " + Arrays.toString(originalSequence) + "\n" +
                (problem.canSequenceBeFormed(originalSequence, sequences) ? "can be formed" : "cannot be formed") + "\n" +
                "with the given sequences described by the edges\n" + sequenceRep;

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean canSequenceBeFormed(int[] originalSequence, int[][] sequences) {
        if (originalSequence.length == 0)
            return false;

        List<Integer> topologicalOrder = new ArrayList<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Queue<Integer> bfsQueue = new LinkedList<>();

        //1. Initialize the graph & in-degrees
        for (int[] edge : sequences) {
            inDegree.putIfAbsent(edge[0], 0);
            inDegree.putIfAbsent(edge[1], 0);

            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
        }

        //2. Build the graph & calculate the in-degrees
        for (int[] edge : sequences) {
            int u = edge[0], v = edge[1];

            graph.get(u).add(v);
            inDegree.put(v, inDegree.get(v) + 1);
        }

        //edge case
        if (originalSequence.length != inDegree.size())
            return false;

        //3. Identify initial vertices (in-degree = 0)
        //& put them in the queue
        for (int u : graph.keySet())
            if (inDegree.get(u) == 0)
                bfsQueue.offer(u);

        //4. Run the Kahn algorithm
        while (!bfsQueue.isEmpty()) {
            //for a unique sequence to be possible,
            //there should be only 1 vertex in queue at each level
            if (bfsQueue.size() > 1)
                return false;

            if (originalSequence[topologicalOrder.size()] != bfsQueue.peek())
                return false;

            int u = bfsQueue.poll();
            topologicalOrder.add(u);

            for (int v : graph.get(u)) {
                inDegree.put(v, inDegree.get(v) - 1);
                if (inDegree.get(v) == 0)
                    bfsQueue.offer(v);
            }
        }

        return topologicalOrder.size() == originalSequence.length;
    }
}
