package stack;

import java.io.PrintWriter;
import java.util.Stack;

class ConvertInFixToPostFix {
    /*-
    Given an infix expression in the form of string str. Convert this infix expression to postfix expression.

    Infix expression: The expression of the form a op b. When an operator is in-between every pair of operands.
    Postfix expression: The expression of the form a b op. When an operator is followed for every pair of operands.

    Note: The order of precedence is: ^ greater than * equals to / greater than + equals to -.

    Example 1:
        Input: str = "a+b*(c^d-e)^(f+g*h)-i"
        Output: abcd^e-fgh*+^*+i-
     */
    public static void main(String[] args) {
        String infixExpression = "a+b*(c^d-e)^(f+g*h)-i";

        ConvertInFixToPostFix problem = new ConvertInFixToPostFix();
        String res = "Infix Expression: " + infixExpression + "\n" +
                "Postfix Expression: " + problem.usingStack(infixExpression);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String usingStack(String infixExpression) {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfixResult = new StringBuilder();

        operatorStack.push('(');
        infixExpression += ")";
        for (char currentChar : infixExpression.toCharArray()) {
            if (Character.isLetterOrDigit(currentChar)) {
                // found an operand
                postfixResult.append(currentChar);
            } else if (currentChar == '(') {
                operatorStack.push(currentChar);
            } else if (currentChar == ')') {
                // pop & output until '(' is not found
                while (!operatorStack.empty() && operatorStack.peek() != '(') {
                    postfixResult.append(operatorStack.peek());
                    operatorStack.pop();
                }

                //remove the '(' from the stack
                operatorStack.pop();
            } else {
                // found an operator => pop all operators from stack where precedence(top) >= precedence(opr)
                // i.e. monotonically increasing stack
                // stack should have operators such that their precedence is inc.
                while (!operatorStack.empty() &&
                        getPrecedence(operatorStack.peek()) >= getPrecedence(currentChar)) {
                    postfixResult.append(operatorStack.peek());
                    operatorStack.pop();
                }
                operatorStack.push(currentChar);
            }
        }

        return postfixResult.toString();
    }

    private int getPrecedence(char operator) {
        if (operator == '+' ||
                operator == '-') {
            return 1;
        } else if (operator == '*' ||
                operator == '/') {
            return 2;
        } else if (operator == '^') {
            return 3;
        } else {
            return -1;
        }
    }
}
