package backtrack;

import java.util.Arrays;

class NQueen {

	public static void main(String[] args) {
		int n = 4;
		solveNQueen(n);
	}

	private static void solveNQueen(int n) {
		int[][] board = new int[n][n];
		for (int[] row : board)
			Arrays.fill(row, 0);

		int startCol = 0;
		if (!solutionExists(board, startCol, n)) {
			System.out.println("No solution");
			return;
		}
		printSolution(board);
	}

	private static boolean solutionExists(int[][] board, int col, int n) {
		if (col >= n)
			return true;

		for (int row = 0; row < n; row++) {
			if (isSafeAllotment(board, row, col, n)) {
				board[row][col] = 1;

				if (solutionExists(board, col + 1, n))
					return true;

				board[row][col] = 0;
			}
		}
		return false;
	}

	private static boolean isSafeAllotment(int[][] board, int row, int col, int n) {
		int i, j;

		for (j = 0; j < col; j++)
			if (board[row][j] == 1)
				return false;

		/* Check upper diagonal on left side */
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;

		/* Check lower diagonal on left side */
		for (i = row, j = col; j >= 0 && i < n; i++, j--)
			if (board[i][j] == 1)
				return false;

		return true;
	}

	private static void printSolution(int[][] board) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
	}
}
