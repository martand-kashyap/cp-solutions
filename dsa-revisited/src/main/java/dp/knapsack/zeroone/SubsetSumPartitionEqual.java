package dp.knapsack.zeroone;

import java.io.PrintWriter;

class SubsetSumPartitionEqual {
    private final SubsetSum subsetSum;

    SubsetSumPartitionEqual() {
        subsetSum = new SubsetSum();
    }

    private boolean equalPartitionSubsetR(int[] items, int n) {
        int sum = 0;
        for (int item : items)
            sum += item;

        if (sum % 2 != 0)
            return false;

        return subsetSum.subsetExistsWithSumR(items, sum / 2, n);
    }

    private boolean equalPartitionSubsetM(int[] items, int n) {
        int sum = 0;
        for (int item : items)
            sum += item;

        if (sum % 2 != 0)
            return false;

        return subsetSum.subsetExistsWithSumM(items, sum / 2, n);
    }

    private boolean equalPartitionSubsetT(int[] items, int n) {
        int sum = 0;
        for (int item : items)
            sum += item;

        if (sum % 2 != 0)
            return false;

        return subsetSum.subsetExistsWithSumT(items, sum / 2, n);
    }

    public static void main(String[] args) {
        int[] items = { 2, 3, 1, 4 };
        int n = items.length;

        SubsetSumPartitionEqual equalPartition = new SubsetSumPartitionEqual();

        String sb = "Recursive : " + equalPartition.equalPartitionSubsetR(items, n) + "\n"
                + "Memoized (Top-Down) : " + equalPartition.equalPartitionSubsetM(items, n) + "\n"
                + "Tabulation (Bottom-Up) : " + equalPartition.equalPartitionSubsetT(items, n);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(sb);

        pw.close();
    }
}
