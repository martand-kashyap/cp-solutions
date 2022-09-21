package famousproblems;

import java.io.PrintWriter;
import java.util.Arrays;

class BestTimeToBuyAndSellStockI {
    /*-
        AT MOST 1 TRANSACTION

        Given an array 'prices' where prices[i] is the price of a given stock on the ith day.
        You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
        Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

        Input: prices = [7,1,5,3,6,4]
        Output: 5
        Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
        Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
     */
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int n = prices.length;

        BestTimeToBuyAndSellStockI bss = new BestTimeToBuyAndSellStockI();
        String res =
                "The max profit in the price list\n" + Arrays.toString(prices) + "\n\n" +
                        "Bruteforce T(n) = O(n^2) : " + bss.findMaxProfitB(n, prices) + "\n" +
                        "Optimized T(n) = O(n) : " + bss.findMaxProfitO(n, prices) + "\n" +
                        "Kadane T(n) = O(n) : " + bss.findMaxProfitK(n, prices);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int findMaxProfitB(int n, int[] prices) {
        int globalMaxProfit = Integer.MIN_VALUE, localMaxProfit;

        if (n <= 1) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                localMaxProfit = prices[j] - prices[i];
                globalMaxProfit = Integer.max(globalMaxProfit, localMaxProfit);
            }
        }

        return globalMaxProfit;
    }

    private int findMaxProfitO(int n, int[] prices) {
        int globalMaxProfit = Integer.MIN_VALUE, minPrice = Integer.MAX_VALUE;

        if (n <= 1) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            minPrice = Integer.min(minPrice, prices[i]);
            globalMaxProfit = Integer.max(globalMaxProfit, prices[i] - minPrice);
        }

        return globalMaxProfit;
    }

    private int findMaxProfitK(int n, int[] prices) {
        int globalMaxProfit = Integer.MIN_VALUE, localProfit = 0;

        if (n <= 1) {
            return 0;
        }

        for (int i = 1; i < n; i++) {
            localProfit = Integer.max(0, localProfit + prices[i] - prices[i - 1]);
            globalMaxProfit = Integer.max(globalMaxProfit, localProfit);
        }

        return globalMaxProfit;
    }
}
