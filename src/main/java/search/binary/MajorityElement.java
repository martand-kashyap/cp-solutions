package search.binary;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class MajorityElement {
    public static void main(String[] args) {
        int[] arr = {3, 3, 4, 2, 4, 4, 2, 4, 4};
        MajorityElement me = new MajorityElement();

        String res = "Majority Element in\n" + me.usingMap(arr);
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int usingMap(int[] arr) {
        int n = arr.length, result = -1;
        Map<Integer, Integer> elementFrequency = new HashMap<>();
        for (int element : arr) {
            elementFrequency.put(element, elementFrequency.getOrDefault(element, 0) + 1);

            if (elementFrequency.get(element) > n / 2)
                result = element;
        }

        return result;
    }

    private int usingVotingMethod(int[] arr) {
        int candidate = 0, count = -1;
//
//        for (int i = 0; i < arr.length; i += 1) {
//            if (candidate == 0) {
//                candidate = arr[i];
//                votes = 1;
//            }
//
//            if (arr[i] == candidate)
//                votes++;
//            else
//                votes--;
//        }
//
//        System.out.println(candidate);
//
//        int count = 0;
//        for (int i = 0; i < arr.length; i++)
//            if (arr[i] == candidate)
//                count += 1;
//
//        return (count >= arr.length / 2) ? candidate : -1;
        for (int num : arr) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
