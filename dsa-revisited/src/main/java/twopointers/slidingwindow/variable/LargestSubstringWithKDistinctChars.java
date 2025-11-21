package twopointers.slidingwindow.variable;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class LargestSubstringWithKDistinctChars {
    /*-
    Given a string you need to print longest possible substring that has exactly k unique characters.
    If there are more than one substring of longest possible length, then print any one of them.

    Example:
        Input: string = "aabbcc", k = 3
        Output: 6
        Explanation:
            There are substrings with exactly 3 unique characters
            {“aabbcc” , “abbcc” , “aabbc” , “abbc” }
            Max is “aabbcc” with length 6.
     */
    public static void main(String[] args) {
        String input = "aabbcc";
        int k = 3;

        LargestSubstringWithKDistinctChars problem = new LargestSubstringWithKDistinctChars();

        String res =
                "Length of the longest possible substring that has exactly k = " + k +
                        " unique characters in the string" + "\n" + input +
                        "Bruteforce T(n) = O(n^2) : " + problem.solveB(input, k) + "\n" +
                        "Sliding Window T(n) = O(n) : " + problem.solveSW(input, k) + "\n" +
                        "Sliding Window T(n) = O(n) [Alternative Impl] : " + problem.solveSWAlternativeImpl(input, k);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int solveB(String input, int k) {
        return -1;
    }

    private int solveSW(String input, int k) {
        int n = input.length(), left = 0, right = 0, result = 0;

        Map<Character, Integer> charCountMap = new HashMap<>();
        while (right < n) {
            charCountMap.put(input.charAt(right), charCountMap.getOrDefault(input.charAt(right), 0) + 1);

            if (charCountMap.size() < k)
                right += 1;
            else if (charCountMap.size() > k) {
                while (charCountMap.size() > k) {
                    charCountMap.put(input.charAt(left), charCountMap.get(input.charAt(left)) - 1);

                    if (charCountMap.get(input.charAt(left)) == 0)
                        charCountMap.remove(input.charAt(left));

                    left += 1;
                }
                right += 1;
            } else {
                result = Integer.max(result, right - left + 1);
                right += 1;
            }
        }

        return result > 0 ? result : -1;
    }


    private int solveSWAlternativeImpl(String input, int k) {
        int n = input.length(), left = 0, result = 0;

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (int right = 0; right < n; right++) {
            charCountMap.put(input.charAt(right), charCountMap.getOrDefault(input.charAt(right), 0) + 1);

            while (charCountMap.size() > k) {
                charCountMap.put(input.charAt(left), charCountMap.get(input.charAt(left)) - 1);

                if (charCountMap.get(input.charAt(left)) == 0)
                    charCountMap.remove(input.charAt(left));

                left += 1;
            }

            if (charCountMap.size() == k)
                result = Integer.max(result, right - left + 1);
        }

        return result > 0 ? result : -1;
    }
}
