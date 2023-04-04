package search.binary.space;

import java.io.PrintWriter;

class KokoEatingBananas {
    /*
    Koko loves to eat bananas.
    There are n piles of bananas, the ith pile has piles[i] bananas.
    The guards have gone and will come back in h hours.

    Koko can decide her bananas-per-hour eating speed of k.
    Each hour, she chooses some pile of bananas and eats k bananas from that pile.
    If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

    Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
    Return the minimum integer k such that she can eat all the bananas within h hours.

    Example 1:
    Input: piles = [3,6,7,11], h = 8
    Output: 4

    Example 2:
    Input: piles = [30,11,23,4,20], h = 5
    Output: 30

    Example 3:
    Input: piles = [30,11,23,4,20], h = 6
    Output: 23
     */
    public static void main(String[] args) {
        int[] piles = {30, 11, 23, 4, 20};
        int hours = 5;

        KokoEatingBananas problem = new KokoEatingBananas();
        String res = "Koko can eat bananas at k/hour" + "\n\n" +
                "Bruteforce : " + problem.bruteforce(piles, hours) + "\n" +
                "Binary Search on search space : " + problem.minEatingSpeed(piles, hours);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    public int bruteforce(int[] piles, int hours) {
        int left = 1, right = 1;

        for (int pile : piles) {
            right = Integer.max(right, pile);
            //T(n) = O(n)
        }

        for (int k = left; k <= right; k += 1) {
            if (canEatAllBananas(piles, k, hours)) {
                return k;
            }
        }

        return -1;
        //T(n) = O(max(n).n)
    }

    public int minEatingSpeed(int[] piles, int hours) {
        int left = 1, right = 1;

        //get the range for applying binary Search
        for (int pile : piles) {
            right = Integer.max(right, pile);
        }

        int res = -1;
        while (left <= right) {
            int k = left + (right - left) / 2;

            if (canEatAllBananas(piles, k, hours)) {
                res = k;
                right = k - 1;
            } else {
                left = k + 1;
            }
        }

        //T(n) = O(log(max(n)).n)
        return res;
    }

    private boolean canEatAllBananas(int[] piles, int k, int hours) {
        int totalTimeRequired = 0;
        for (int pile : piles) {
            int timeOnPile = (int) Math.ceil((double) pile / k);
            totalTimeRequired += timeOnPile;

            if (totalTimeRequired > hours) return false;
        }

        return true;
        //T(n) = O(n)
    }
}
