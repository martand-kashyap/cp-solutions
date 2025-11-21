package twopointers.slidingwindow.fixed;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
        if (pattern.length() > input.length()) {
            return isAnagram(pattern, input);
        }

        char[] a = input.toLowerCase().toCharArray(), b = pattern.toLowerCase().toCharArray();

        Map<Character, Integer> alphabetCountMap = new HashMap<>();

        for (char c : a) {
            alphabetCountMap.put(c, alphabetCountMap.getOrDefault(c, 0) + 1);
        }
        for (char c : b) {
            alphabetCountMap.put(c, alphabetCountMap.getOrDefault(c, 0) - 1);
        }

        for (int count : alphabetCountMap.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    private int solveSW(String input, String pattern) {
        int result = 0, left = 0, right = 0, n = input.length(), k = pattern.length();

        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for (int i = 0; i < k; i++)
            charCountMap.put(pattern.charAt(i), charCountMap.getOrDefault(pattern.charAt(i), 0) + 1);
        int uniqueCharInPattern = charCountMap.size();

        while (right < n) {
            char currentChar = input.charAt(right);

            if (charCountMap.containsKey(currentChar))
                charCountMap.put(currentChar, charCountMap.get(currentChar) - 1);
            if (charCountMap.containsKey(currentChar) && charCountMap.get(currentChar) == 0)
                uniqueCharInPattern -= 1;

            int currentSWSize = right - left + 1;
            if (currentSWSize < k)
                right += 1;

            else if (currentSWSize == k) {
                result += (uniqueCharInPattern == 0) ? 1 : 0;

                if (charCountMap.containsKey(input.charAt(left))) {
                    charCountMap.put(input.charAt(left), charCountMap.get(input.charAt(left)) + 1);

                    if (charCountMap.get(input.charAt(left)) == 1) {
                        uniqueCharInPattern += 1;
                    }
                }

                //slide the window
                left += 1;
                right += 1;
            }
        }

        return result;
    }
}
