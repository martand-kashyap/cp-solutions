package search.binary;

import java.io.PrintWriter;
import java.util.Arrays;

class BitonicArray {
    public static void main(String[] args) {
        PeakElement pe = new PeakElement();
        OrderAgnosticSearch bs = new OrderAgnosticSearch();

        int[] nums = {3, 9, 10, 20, 17, 5, 1};
        int n = nums.length, target = 1;

        int peakIndex = pe.findPeakElement(nums);
        int r1 = bs.binarySearchAsc(nums, 0, peakIndex - 1, target);
        int r2 = bs.binarySearchDsc(nums, peakIndex, n - 1, target);
        int index = r1 == -1 && r2 == -1 ? -1 : Math.max(r1, r2);

        String res = "Search key (" + target + ") found at index : " + index + "\n"
                + "in the Bitonic array\n" + Arrays.toString(nums);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }
}
