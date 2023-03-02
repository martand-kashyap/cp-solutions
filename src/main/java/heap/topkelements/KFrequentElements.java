package heap.topkelements;

import java.io.PrintWriter;
import java.util.*;

class KFrequentElements {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        KFrequentElements kfe = new KFrequentElements();
        int[] result = kfe.topKFrequent(nums, k);
        String res = "k (=" + k + ") most frequent elements in the list:\n" + Arrays.toString(nums)
                + "\nare : - " + Arrays.toString(result);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> elementFrequency = new HashMap<>(nums.length);
        //PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (int element : nums) {
            elementFrequency.put(element, elementFrequency.getOrDefault(element, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : elementFrequency.entrySet()) {
            minHeap.add(entry);

            if (minHeap.size() > k)
                minHeap.poll();
        }

        for (int i = 0; !minHeap.isEmpty(); i++) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }
}
