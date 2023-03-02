package twopointers.opposite;

import java.io.PrintWriter;

class IsValidPalindrome {
    /*-
    A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters,
    it reads the same forward and backward.
    Alphanumeric characters include letters and numbers.

    Given a string s, return true if it is a palindrome, or false otherwise.

    Example 1:
        Input: s = "A man, a plan, a canal: Panama"
        Output: true
        Explanation: "amanaplanacanalpanama" is a palindrome.

    Example 2:
        Input: s = "race a car"
        Output: false
        Explanation: "raceacar" is not a palindrome.
    */
    public static void main(String[] args) {
        String input = "A man, a plan, a canal: Panama";

        IsValidPalindrome problem = new IsValidPalindrome();

        String res = "The input string : " + input + "\n" +
                "is valid palindrome? " + problem.using2P(input);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean using2P(String input) {
        for (int left = 0, right = input.length() - 1; left < right; left++, right--) {
            while (left < right && !Character.isLetterOrDigit(input.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(input.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(input.charAt(left)) != Character.toLowerCase(input.charAt(right)))
                return false;
        }

        return true;
    }
}
