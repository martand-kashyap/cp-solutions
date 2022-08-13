package backtrack;

import java.util.Arrays;

class Sudoku {
    public static void main(String[] args) {
        int[][] sudokuBoard = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        int n = 9;

        if (solutionExists(sudokuBoard, 0, 0, n))
            printSolvedBoard(sudokuBoard);
        else
            System.out.println("no solution");
    }

    private static void printSolvedBoard(int[][] sudokuBoard) {
        for (int[] row : sudokuBoard) {
            Arrays.stream(row).forEach(rowItem -> System.out.print(rowItem + " "));
            System.out.println();
        }
    }

    private static boolean solutionExists(int[][] board, int r, int c, int n) {
        if (r == n - 1 && c == n)
            return true;

        if (c == n) {
            r += 1;
            c = 0;
        }

        if (board[r][c] != 0)
            return solutionExists(board, r, c + 1, n);

        for (int o = 1; o < 10; o++) {
            if (isValidAllocation(board, r, c, n, o)) {
                board[r][c] = o;

                if (solutionExists(board, r, c + 1, n))
                    return true;

                board[r][c] = 0;
            }
        }

        return false;
    }

    private static boolean isValidAllocation(int[][] board, int r, int c, int n, int candidate) {
        for (int i = 0; i < n; i++)
            if (board[i][c] == candidate)
                return false;

        for (int j = 0; j < n; j++)
            if (board[r][j] == candidate)
                return false;

        int subMatrixStartRow = r / 3 * 3, subMatrixStartCol = c / 3 * 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[subMatrixStartRow + i][subMatrixStartCol + j] == candidate)
                    return false;

        return true;
    }
}
