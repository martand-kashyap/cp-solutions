package stack;

import java.io.PrintWriter;
import java.util.Stack;

class BackspaceStringCompare {
    /*-
    Given two strings s and t, return true if they are equal when both are typed into empty text editors.
    '#' means a backspace character.
    Note that after backspacing an empty text, the text will continue empty.

    Example 1:

    Input: s = "ab#c", t = "ad#c"
    Output: true
    Explanation: Both s and t become "ac".

    Example 2:

    Input: s = "ab##", t = "c#d#"
    Output: true
    Explanation: Both s and t become "".

    Example 3:

    Input: s = "a#c", t = "b"
    Output: false
    Explanation: s becomes "c" while t becomes "b".
     */
    public static void main(String[] args) {
        String s = "ab#c", t = "ad#c";

        BackspaceStringCompare problem = new BackspaceStringCompare();

        String res = "The input strings" + "\n" +
                "s : " + s + "\n" +
                "t : " + t + "\n" +
                "after processing '#' are same?" + "\n\n" +
                "using stack : " + problem.usingStack(s, t) + "\n" +
                "using 1 ptr : " + problem.using1Pointer(s, t);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean usingStack(String s, String t) {
        return processUsingStack(s)
                .equals(processUsingStack(t));
        // T(n) = O(m+n)
        // S(n) = O(m+n)
    }

    private String processUsingStack(String input) {
        Stack<Character> processedChars = new Stack<>();

        for (char currentChar : input.toCharArray()) {
            if (currentChar != '#') {
                processedChars.push(currentChar);
            } else if (!processedChars.isEmpty()) {
                processedChars.pop();
            }
        }

        return String.valueOf(processedChars);
    }

    private boolean using1Pointer(String s, String t) {
        return processUsing1Pointer(s)
                .equals(processUsing1Pointer(t));
        // T(n) = O(m+n)
        // S(n) = O(1)
    }

    private String processUsing1Pointer(String input) {
        int backspaceCount = 0, n = input.length();
        StringBuilder processedChars = new StringBuilder();

        for (int i = n - 1; i >= 0; i--) {
            char currentChar = input.charAt(i);

            if (currentChar == '#')
                backspaceCount++;
            else if (backspaceCount > 0)
                backspaceCount--;
            else
                processedChars.append(currentChar);
        }

        return String.valueOf(processedChars);
    }
}
