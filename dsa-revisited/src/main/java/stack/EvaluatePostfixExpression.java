package stack;

import java.io.PrintWriter;
import java.util.Stack;

class EvaluatePostfixExpression {
    /*-
        The Postfix notation is used to represent algebraic expressions.
        The expressions written in postfix form are evaluated faster compared to infix notation as parenthesis
        is not required in postfix.

        Example 1:
        Input: str = “2 3 1 * + 9 -“
        Output: -4
     */
    public static void main(String[] args) {
        String postfixExpression = "231*+9-";

        EvaluatePostfixExpression problem = new EvaluatePostfixExpression();
        String res = "The postfix expression: " + postfixExpression + "\n" +
                "evaluates to: " + problem.evaluatePostfixUsingStack(postfixExpression);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private double evaluatePostfixUsingStack(String postfixExpression) {
        Stack<Double> operandStack = new Stack<>();

        for (char currentChar : postfixExpression.toCharArray()) {
            if (Character.isDigit(currentChar)) {
                // push operand on stack
                operandStack.push((double) Character.getNumericValue(currentChar));
            } else {
                // pop 2 operands
                double b = operandStack.pop(), a = operandStack.pop();
                double result = 0;

                switch (currentChar) {
                    case '+':
                        result = a + b;
                        break;
                    case '-':
                        result = a - b;
                        break;
                    case '*':
                        result = a * b;
                        break;
                    case '/':
                        result = a / b;
                        break;
                    case '^':
                        result = Math.pow(a, b);
                        break;
                }

                operandStack.push(result);
            }
        }

        return operandStack.peek();
    }
}
