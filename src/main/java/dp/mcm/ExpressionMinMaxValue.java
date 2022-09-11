package dp.mcm;

import java.io.PrintWriter;
import java.util.Arrays;

class ExpressionMinMaxValue {
    /*-
        Given an expression which contains numbers and two operators ‘+’ and ‘*’,
        we need to find maximum and minimum value which can be obtained by evaluating this expression
        by different parenthesization.

        Input  : expr = “1+2*3+4*5”
        Output : Minimum Value = 27, Maximum Value = 105

        Explanation:
        Minimum evaluated value = 1 + (2*3) + (4*5) = 27
        Maximum evaluated value = (1 + 2)*(3 + 4)*5 = 105
    */
    public static void main(String[] args) {
        String expression = "1+2*3+4*5";
        ExpressionMinMaxValue minMax = new ExpressionMinMaxValue();

        String res = "Recursive T(n) = O(n.2^n))" + "\n\t" + "Minimum Value : " + minMax.findMinValueR(expression) + "\n\t" +
                "Maximum Value : " + minMax.findMaxValueR(expression) + "\n\n" +
                "Memoized (Top-Down) T(n) = O(n.n^2)" + "\n\t" + "Minimum Value : " + minMax.findMinValueM(expression) + "\n\t" +
                "Maximum Value : " + minMax.findMaxValueM(expression);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int findMinValueR(String expression) {
        return solveMinR(expression.toCharArray(), 0, expression.length() - 1);
    }

    private int findMaxValueR(String expression) {
        return solveMaxR(expression.toCharArray(), 0, expression.length() - 1);
    }

    private int solveMinR(char[] expression, int i, int j) {
        if (i > j)
            return 0;

        if (i == j)
            //return Integer.parseInt(String.valueOf(expression[i]));
            //return expression[i] - '0';
            return Character.getNumericValue(expression[i]);

        int minValue = Integer.MAX_VALUE;

        for (int k = i + 1; k <= j - 1; k += 2) {
            int temp1 = solveMinR(expression, i, k - 1);
            int temp2 = solveMinR(expression, k + 1, j);

            if (expression[k] == '+') {
                minValue = Math.min(minValue, temp1 + temp2);
            }
            if (expression[k] == '*') {
                minValue = Math.min(minValue, temp1 * temp2);
            }
        }

        return minValue;
    }

    private int solveMaxR(char[] expression, int i, int j) {
        if (i > j)
            return 0;

        if (i == j)
            return Character.getNumericValue(expression[i]);

        int maxValue = Integer.MIN_VALUE;

        for (int k = i + 1; k <= j - 1; k += 2) {
            int temp1 = solveMaxR(expression, i, k - 1);
            int temp2 = solveMaxR(expression, k + 1, j);

            if (expression[k] == '+') {
                maxValue = Math.max(maxValue, temp1 + temp2);
            }
            if (expression[k] == '*') {
                maxValue = Math.max(maxValue, temp1 * temp2);
            }
        }

        return maxValue;
    }

    private int findMinValueM(String expression) {
        int n = expression.length();

        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMinM(expression.toCharArray(), 0, n - 1, dp);
    }

    private int findMaxValueM(String expression) {
        int n = expression.length();

        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMaxM(expression.toCharArray(), 0, n - 1, dp);
    }

    private int solveMinM(char[] expression, int i, int j, int[][] dp) {
        if (i > j)
            return 0;

        if (i == j)
            return Character.getNumericValue(expression[i]);

        if (dp[i][j] != -1)
            return dp[i][j];

        int minValue = Integer.MAX_VALUE;

        for (int k = i + 1; k <= j - 1; k += 2) {
            int temp1 = solveMinM(expression, i, k - 1, dp);
            int temp2 = solveMinM(expression, k + 1, j, dp);

            if (expression[k] == '+') {
                minValue = Math.min(minValue, temp1 + temp2);
            }
            if (expression[k] == '*') {
                minValue = Math.min(minValue, temp1 * temp2);
            }
        }

        return dp[i][j] = minValue;
    }

    private int solveMaxM(char[] expression, int i, int j, int[][] dp) {
        if (i > j)
            return 0;

        if (i == j)
            return Character.getNumericValue(expression[i]);

        if (dp[i][j] != -1)
            return dp[i][j];

        int maxValue = Integer.MIN_VALUE;

        for (int k = i + 1; k <= j - 1; k += 2) {
            int temp1 = solveMaxM(expression, i, k - 1, dp);
            int temp2 = solveMaxM(expression, k + 1, j, dp);

            if (expression[k] == '+') {
                maxValue = Math.max(maxValue, temp1 + temp2);
            }
            if (expression[k] == '*') {
                maxValue = Math.max(maxValue, temp1 * temp2);
            }
        }

        return dp[i][j] = maxValue;
    }
}
