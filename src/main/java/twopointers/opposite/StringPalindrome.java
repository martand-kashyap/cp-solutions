package twopointers.opposite;

import java.io.PrintWriter;

class StringPalindrome {
    public static void main(String[] args) {
        String s = "abbba";

        StringPalindrome problem = new StringPalindrome();

        String res = "The given string : " + s + "\n\n" +
                "Two pointers (opposite direction) T(n) = O(n) : " + problem.solve2P(s) + "\n" +
                "Recursive T(n) = O(n) : " + problem.isPalindromeR(s);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean solve2P(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    private boolean isPalindromeR(String s) {
        return solveR(s, 0, s.length() - 1);
    }

    private boolean solveR(String s, int i, int j) {
        if (i >= j)
            return true;

        if (s.charAt(i) != s.charAt(j))
            return false;

        return solveR(s, i + 1, j - 1);
    }
}
