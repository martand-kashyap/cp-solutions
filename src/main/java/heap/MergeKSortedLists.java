package heap;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.PrintWriter;
import java.util.*;

class MergeKSortedLists {
    public static void main(String[] args) {
        int[][] lists = {{1, 2, 3, 4}, {2, 2, 3, 4}, {5, 5, 6, 6}, {7, 8, 9, 9}};
        int k = lists.length;

        MergeKSortedLists sortedLists = new MergeKSortedLists();
        String result = sortedLists.mergeKSortedLists(lists, k);

        StringBuffer res = new StringBuffer("The ");
        res.append(k).append(" sorted lists\n");
        for (int[] list : lists) {
            res.append(Arrays.toString(list)).append("\n");
        }
        res.append("have been merged as: -\n");
        res.append(result);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String mergeKSortedLists(int[][] lists, int k) {
        List<Integer> result = new ArrayList<>();
//        Queue<ListDetails> minHeap = new PriorityQueue<>(k, (node1, node2) -> node1.value - node2.value);
        Queue<ListDetails> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(node -> node.value));

        for (int r = 0; r < k; r += 1) {
            minHeap.add(new ListDetails(r, 0, lists[r][0]));
        }

        int i = 0;
        while (!minHeap.isEmpty()) {
            ListDetails temp = minHeap.peek();

            result.add(temp.value);

            if (temp.indexInList + 1 < lists[temp.listNumber].length) {
                minHeap.add(new ListDetails(temp.listNumber, temp.indexInList + 1, lists[temp.listNumber][temp.indexInList + 1]));
            }

            minHeap.poll();
        }

        return result.toString();
    }

    @AllArgsConstructor
    @ToString
    static class ListDetails {
        int listNumber, indexInList, value;
    }
}
