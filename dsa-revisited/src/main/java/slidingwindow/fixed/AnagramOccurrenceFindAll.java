package slidingwindow.fixed;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class AnagramOccurrenceFindAll {
    /*-
    Given two strings s and p, return an array of all the start indices of p's anagrams in s.

    Example:
        Input: s = "cbaebabacd", p = "abc"
        Output: [0,6]
        Explanation:
            The substring with start index = 0 is "cba", which is an anagram of "abc".
            The substring with start index = 6 is "bac", which is an anagram of "abc".
     */
    public static void main(String[] args) {
        String input = "forxxorfxdofr", pattern = "for";

        AnagramOccurrenceFindAll aoc = new AnagramOccurrenceFindAll();
        String res =
                "The pattern " + pattern + " appears in the input string at the indices" + input + "\n" +
                        "Bruteforce T(n) = O(n*k) : " + aoc.solveB(input, pattern) + "\n" +
                        "Sliding Window T(n) = O(n) : " + aoc.solveSW(input, pattern);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private List<Integer> solveB(String input, String pattern) {
        int n = input.length(), x = pattern.length();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n - x + 1; i++) {
            String subString = input.substring(i, i + x);

            if (isAnagram(subString, pattern))
                result.add(i);
        }

        return result;
    }

    private boolean isAnagram(String input, String pattern) {
        char[] a = input.toLowerCase().toCharArray(), b = pattern.toLowerCase().toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    private List<Integer> solveSW(String input, String pattern) {
        int left = 0, right = 0, n = input.length(), k = pattern.length();
        List<Integer> result = new ArrayList<>();

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
                if (uniqueCharInPattern == 0)
                    result.add(left);

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
