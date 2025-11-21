package famousproblems.buysellstock;

import java.io.PrintWriter;
import java.util.Arrays;

class BestTimeToBuyAndSellStockII {
    /*-
        INFINITE TRANSACTIONS

        You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
        On each day, you may decide to buy and/or sell the stock.
        You can only hold at most one share of the stock at any time.
        However, you can buy it then immediately sell it on the same day.
        Find and return the maximum profit you can achieve.

        Input: prices = [7,1,5,3,6,4]
        Output: 7
        Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
        Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
        Total profit is 4 + 3 = 7.
     */
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int n = prices.length;

        BestTimeToBuyAndSellStockII bss = new BestTimeToBuyAndSellStockII();
        String res = "The max profit in the price list\n" + Arrays.toString(prices) + "\n" +
                "Bruteforce T(n) = O(n^2) : " + bss.findMaxProfitB(n, prices) + "\n" +
                "Kadane T(n) = O(n) : " + bss.findMaxProfitO(n, prices);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int findMaxProfitB(int n, int[] prices) {
        int globalMaxProfit = Integer.MIN_VALUE;

        return globalMaxProfit;
    }

    private int findMaxProfitO(int n, int[] prices) {
        int globalMaxProfit = Integer.MIN_VALUE, localMaxProfit = 0;

        if (prices == null || n <= 1) {
            return 0;
        }

        return globalMaxProfit;
    }
}
