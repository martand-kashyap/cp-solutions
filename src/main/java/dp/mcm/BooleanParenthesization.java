package dp.mcm;

import java.io.PrintWriter;
import java.util.Arrays;

class BooleanParenthesization {
    /*-
        Given a boolean expression S of length N with following symbols.
        Symbols
            'T' ---> true
            'F' ---> false
        and following operators filled between symbols
        Operators
            &   ---> boolean AND
            |   ---> boolean OR
            ^   ---> boolean XOR
        Count the number of ways (modulo 1003) we can parenthesize the expression so that the value of expression evaluates to true.

        Input:
            N = 7
            S = T|T&F^T
        Output: 4
        Explaination:
            The expression evaluates to true in 4 ways
            ((T|T)&(F^T)),
            (T|(T&(F^T))),
            (((T|T)&F)^T) and
            (T|((T&F)^T)).
     */
    public static void main(String[] args) {
        String expression = "T|T&F^T";
        int n = expression.length();

        BooleanParenthesization bp = new BooleanParenthesization();
        String res = "Recursive T(n) = O() : " + bp.countWaysR(n, expression) + "\n" +
                "Memoized T(n) = O(n^3) : " + bp.countWaysM(n, expression);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int countWaysR(int n, String expression) {
        return solveR(expression, 0, n - 1, 1);
    }

    private int solveR(String expression, int i, int j, int solveFor) {
        // In an empty input expression, there are 0 ways to evaluate to TRUE.
        if (i > j)
            return 0;

        /*-
            There is only one character in the input expression.
            It will be a symbol (either a T or F).
        */
        if (i == j) {
            if (solveFor == 1)
                // If we are solving for True & the only symbol is 'T'.
                return expression.charAt(i) == 'T' ? 1 : 0;
            else
                // If we are solving for False & the only symbol is 'F'.
                return expression.charAt(i) == 'F' ? 1 : 0;
        }

        int numberOfWays = 0;

        for (int k = i + 1; k <= j - 1; k += 2) {
            // left sub-expression, evaluate to true
            int leftExpressionEvaluatesToTrue = solveR(expression, i, k - 1, 1);
            // left sub-expression, evaluate to false
            int leftExpressionEvaluatesToFalse = solveR(expression, i, k - 1, 0);

            // right sub-expression, evaluate to true
            int rightExpressionEvaluatesToTrue = solveR(expression, k + 1, j, 1);
            // right sub-expression, evaluate to false
            int rightExpressionEvaluatesToFalse = solveR(expression, k + 1, j, 0);

            char currentOperator = expression.charAt(k);

            if (currentOperator == '|') {
                if (solveFor == 1)
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToTrue)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToFalse)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToTrue);
                else
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToFalse);
            }

            if (currentOperator == '&') {
                if (solveFor == 1)
                    numberOfWays += (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToTrue);
                else
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToTrue)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToFalse)
                            + (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToFalse);
            }

            if (currentOperator == '^') {
                if (solveFor == 1)
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToTrue)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToFalse);
                else
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToFalse)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToTrue);
            }
        }

        return (numberOfWays % 1003);
    }

    private int countWaysM(int n, String expression) {
        int[][][] dp = new int[n + 1][n + 1][2];
        for (int[][] t : dp)
            for (int[] r : t)
                Arrays.fill(r, -1);

        return solveM(expression, 0, n - 1, 1, dp);
    }

    private int solveM(String expression, int i, int j, int solveFor, int[][][] dp) {
        if (i > j)
            return 0;

        if (i == j) {
            if (solveFor == 1)
                return expression.charAt(i) == 'T' ? 1 : 0;
            else
                return expression.charAt(i) == 'F' ? 1 : 0;
        }

        if (dp[i][j][solveFor] != -1)
            return dp[i][j][solveFor];

        int numberOfWays = 0;

        for (int k = i + 1; k <= j - 1; k += 2) {
            int leftExpressionEvaluatesToTrue = dp[i][k - 1][1] != -1 ? dp[i][k - 1][1] : solveM(expression, i, k - 1, 1, dp);
            int leftExpressionEvaluatesToFalse = dp[i][k - 1][0] != -1 ? dp[i][k - 1][0] : solveM(expression, i, k - 1, 0, dp);
            int rightExpressionEvaluatesToTrue = dp[k + 1][j][1] != -1 ? dp[k + 1][j][1] : solveM(expression, k + 1, j, 1, dp);
            int rightExpressionEvaluatesToFalse = dp[k + 1][j][0] != -1 ? dp[k + 1][j][0] : solveM(expression, k + 1, j, 0, dp);

            char currentOperator = expression.charAt(k);

            if (currentOperator == '|') {
                if (solveFor == 1)
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToTrue)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToFalse)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToTrue);
                else
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToFalse);
            }

            if (currentOperator == '&') {
                if (solveFor == 1)
                    numberOfWays += (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToTrue);
                else
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToTrue)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToFalse)
                            + (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToFalse);
            }

            if (currentOperator == '^') {
                if (solveFor == 1)
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToTrue)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToFalse);
                else
                    numberOfWays += (leftExpressionEvaluatesToFalse * rightExpressionEvaluatesToFalse)
                            + (leftExpressionEvaluatesToTrue * rightExpressionEvaluatesToTrue);
            }
        }

        return dp[i][j][solveFor] = (numberOfWays % 1003);
    }
}
