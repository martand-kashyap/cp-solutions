package slidingwindow.variable;

import java.io.PrintWriter;
import java.util.*;

class MinimumWindowSubstring {
    /*-
    Given a string and a pattern, find the smallest substring in the given string
    which has all the character occurrences of the given pattern.

    Input: String="aabdec", Pattern="abc"
    Output: "abdec"
    Explanation: The smallest substring having all characters of the pattern is "abdec"
     */
    public static void main(String[] args) {
        String input = "geeksforgeeks", pattern = "ork";

        MinimumWindowSubstring problem = new MinimumWindowSubstring();

        String res =
                "The smallest substring in the given string : " + input + "\n" +
                        "which has all the character occurrences of the pattern : " + pattern + "\n\n" +
                        "Bruteforce T(n) = O(n^2) : " + problem.solveB(input, pattern) + "\n" +
                        "Sliding Window T(n) = O(n) : " + problem.solveSW(input, pattern) + "\n" +
                        "Sliding Window T(n) = O(n) [Alternative Impl] : " + problem.solveSWAlternativeImpl(input, pattern);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String solveB(String input, String pattern) {
        int m = input.length(), n = pattern.length(), result = m;
        String minWindowSubstring = null;

        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                String currString = input.substring(j);
                if (isPatternPresent(currString, pattern) && result > j - i + 1) {
                    result = j - i + 1;
                    minWindowSubstring = input.substring(i, j);
                }
            }
        }

        return minWindowSubstring;
    }

    private boolean isPatternPresent(String input, String pattern) {
        Set<Character> s1 = new HashSet<>();
        Set<Character> s2 = new HashSet<>();

        for (char c : input.toCharArray())
            s1.add(c);
        for (char c : pattern.toCharArray())
            s2.add(c);

        return s1.containsAll(s2);
    }

    private String solveSW(String s, String p) {
        int m = s.length(), n = p.length(), i = 0, j = 0, smallestLength = Integer.MAX_VALUE, left = 0, right = m - 1;
        Map<Character, Integer> charCountInPattern = new HashMap<>();

        if (n > m)
            return "";

        for (char c : p.toCharArray())
            charCountInPattern.put(c, charCountInPattern.getOrDefault(c, 0) + 1);

        int charsRequired = charCountInPattern.size();
        boolean found = false;

        while (j < m) {
            char rightChar = s.charAt(j);
            if (charCountInPattern.containsKey(rightChar)) {
                charCountInPattern.put(rightChar, charCountInPattern.get(rightChar) - 1);
                if (charCountInPattern.get(rightChar) == 0)
                    charsRequired -= 1;
            }

            if (charsRequired > 0)
                j++;
            else {
                while (charsRequired == 0) {
                    char leftChar = s.charAt(i);
                    if (charCountInPattern.containsKey(leftChar)) {
                        charCountInPattern.put(leftChar, charCountInPattern.get(leftChar) + 1);
                        if (charCountInPattern.get(leftChar) > 0)
                            charsRequired += 1;
                    }
                    i += 1;
                }

                if (j - i + 1 < smallestLength) {
                    left = i;
                    right = j;
                    smallestLength = j - i + 1;

                    found = true;
                }
                j++;
            }
        }

        return found ? s.substring(left - 1, right + 1) : "";
    }

    private String solveSWAlternativeImpl(String s, String p) {
        int m = s.length(), n = p.length(), i = 0, j, smallestLength = Integer.MAX_VALUE, left = 0, right = m - 1;
        Map<Character, Integer> charCountInPattern = new HashMap<>();

        if (n > m)
            return "";

        for (char c : p.toCharArray())
            charCountInPattern.put(c, charCountInPattern.getOrDefault(c, 0) + 1);

        int charsRequired = charCountInPattern.size();
        boolean found = false;

        for (j = 0; j < m; j++) {
            char rightChar = s.charAt(j);
            if (charCountInPattern.containsKey(rightChar)) {
                charCountInPattern.put(rightChar, charCountInPattern.get(rightChar) - 1);
                if (charCountInPattern.get(rightChar) == 0)
                    charsRequired -= 1;
            }

            while (charsRequired == 0) {
                char leftChar = s.charAt(i);
                if (charCountInPattern.containsKey(leftChar)) {
                    charCountInPattern.put(leftChar, charCountInPattern.get(leftChar) + 1);
                    if (charCountInPattern.get(leftChar) > 0)
                        charsRequired += 1;
                }

                if (j - i + 1 < smallestLength) {
                    left = i;
                    right = j;
                    smallestLength = j - i + 1;

                    found = true;
                }
                i += 1;
            }
        }

        return found ? s.substring(left, right + 1) : "";
    }
}
