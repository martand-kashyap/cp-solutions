package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class FrequencySort {
    public static void main(String[] args) {

    }

    private int[] sortByFrequency(int[] arr, int n) {
        int[] result = new int[n];
        Map<Integer, Integer> elementFrequency = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(elementFrequency.size(), (e1, e2) -> e1.getValue() - e2.getValue());

        for (int element : arr) {
            elementFrequency.put(element, elementFrequency.getOrDefault(element, 0) + 1);
        }

        minHeap.addAll(elementFrequency.entrySet());

        int i = 0;
        while (!minHeap.isEmpty()) {
            Map.Entry<Integer, Integer> temp = minHeap.poll();

            while (temp.getValue() > 0) {
                result[i++] = temp.getKey();
                temp.setValue(temp.getValue() - 1);
            }
        }

        return result;
    }
}
