package twopointers.opposite;

import java.io.PrintWriter;
import java.util.Stack;

class StringPalindrome {
    public static void main(String[] args) {
        String s = "abbba";

        StringPalindrome problem = new StringPalindrome();

        String res = "The given string : " + s + "\n\n" +
                "Two pointers (opposite direction) T(n) = O(n) : " + problem.solve2P(s) + "\n" +
                "Recursive T(n) = O(n) : " + problem.isPalindromeR(s) + "\n" +
                "Stack T(n) = O(n) : " + problem.solveStack(s);

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

    private boolean solveStack(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length(), i = 0;

        while (i < n / 2) {
            stack.push(s.charAt(i));
            i += 1;
        }

        if (n % 2 == 1)
            i += 1;

        while (i < n && s.charAt(i) == stack.peek()) {
            stack.pop();
            i += 1;
        }

        return stack.isEmpty();
    }
}
