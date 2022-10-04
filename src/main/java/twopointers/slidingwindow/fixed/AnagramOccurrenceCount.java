package twopointers.slidingwindow.fixed;

import java.io.PrintWriter;
import java.util.Arrays;

public class AnagramOccurrenceCount {
    /*-
    Given a string and a pattern, return the count of occurrences of the anagrams of the pattern in the given input.

    Example:
    Input : string = forxxorfxdofr, word = for
    Output : 3
    Explanation : Anagrams of the word for - for, orf,ofr
            appear in the text and hence the count is 3.
     */
    public static void main(String[] args) {
        String input = "forxxorfxdofr", pattern = "for";

        AnagramOccurrenceCount aoc = new AnagramOccurrenceCount();
        String res = "The #times pattern " + pattern + " appears in the input string " + input + "\n" +
                "Bruteforce T(n) = O(n*k) : " + aoc.solveB(input, pattern) + "\n" +
                "Sliding Window T(n) = O(n) : " + aoc.solveSW(input, pattern);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int solveB(String input, String pattern) {
        int result = 0, n = input.length(), x = pattern.length();

        for (int i = 0; i < n - x + 1; i++) {
            String subString = input.substring(i, i + x);

            result += isAnagram(subString, pattern) ? 1 : 0;
        }

        return result;
    }

    private boolean isAnagram(String input, String pattern) {
        char[] a = input.toLowerCase().toCharArray(), b = pattern.toLowerCase().toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    private int solveSW(String input, String pattern) {
        int result = 0;

        return result;
    }
}
