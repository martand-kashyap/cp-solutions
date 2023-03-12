package stack;

import java.io.PrintWriter;
import java.util.Stack;

class BalancedParenthesisChecker {
    public static void main(String[] args) {
        String input = "[(])";

        BalancedParenthesisChecker bpc = new BalancedParenthesisChecker();
        String res = "Input is balanced? : " + bpc.isExpressionBalanced(input);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean isExpressionBalanced(String input) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i += 1) {
            char ic = input.charAt(i);
            if (ic == '(' || ic == '{' || ic == '[') {
                stack.push(ic);
            } else if (stack.empty() ||
                    (ic == ')' && stack.peek() != '(') ||
                    (ic == '}' && stack.peek() != '{') ||
                    (ic == ']' && stack.peek() != '[')) {
                return false;
            } else {
                stack.pop();
            }
        }

        return stack.empty();
    }
}
