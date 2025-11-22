package slidingwindow.variable;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class LargestSubstringWith0RepeatingChars {
    /*-
    Given a string, find the length of the longest substring, which has all distinct characters.

    Example:
        Input: String="aabccbb"
        Output: 3
        Explanation: The longest substring with distinct characters is "abc".
     */
    public static void main(String[] args) {
        String input = "pwwkew";

        LargestSubstringWith0RepeatingChars problem = new LargestSubstringWith0RepeatingChars();

        String res =
                "Length of the longest substring, which has all distinct characters in the string : " + input + "\n" +
                        "Bruteforce T(n) = O(n^2) : " + problem.solveB(input) + "\n" +
                        "Sliding Window T(n) = O(n) : " + problem.solveSW(input) + "\n" +
                        "Sliding Window T(n) = O(n) [Alternative Impl] : " + problem.solveSWAlternativeImpl(input);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int solveB(String input) {
        return -1;
    }

    private int solveSW(String input) {
        int n = input.length(), left = 0, right = 0, result = 0;

        Map<Character, Integer> charCountMap = new HashMap<>();
        while (right < n) {
            charCountMap.put(input.charAt(right), charCountMap.getOrDefault(input.charAt(right), 0) + 1);

            if (charCountMap.size() > right - left + 1)
                right++;
            else if (charCountMap.size() < right - left + 1) {
                while (charCountMap.size() < right - left + 1) {
                    charCountMap.put(input.charAt(left), charCountMap.get(input.charAt(left)) - 1);

                    if (charCountMap.get(input.charAt(left)) == 0)
                        charCountMap.remove(input.charAt(left));

                    left += 1;
                }
                right++;
            } else {
                result = Integer.max(result, right - left + 1);
                right++;
            }
        }

        return result > 0 ? result : -1;
    }

    private int solveSWAlternativeImpl(String input) {
        int n = input.length(), left = 0, result = 0;

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (int right = 0; right < n; right++) {
            charCountMap.put(input.charAt(right), charCountMap.getOrDefault(input.charAt(right), 0) + 1);

            while (charCountMap.size() < right - left + 1) {
                charCountMap.put(input.charAt(left), charCountMap.get(input.charAt(left)) - 1);

                if (charCountMap.get(input.charAt(left)) == 0)
                    charCountMap.remove(input.charAt(left));

                left += 1;
            }

            if (charCountMap.size() == right - left + 1)
                result = Integer.max(result, right - left + 1);
        }

        return result > 0 ? result : -1;
    }
}
