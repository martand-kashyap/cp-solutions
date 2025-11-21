package backtrack;

import java.util.Arrays;

class KnightsTour {

	public static void main(String[] args) {
		int n = 8;
		solveKnightsTour(n);
	}

	private static void solveKnightsTour(int n) {
		int[] xMoves = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int[] yMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };

		int[][] solution = new int[n][n];
		for (int[] row : solution)
			Arrays.fill(row, -1);

		solution[0][0] = 0;
		if (!backtrack(0, 0, 1, solution, xMoves, yMoves, n)) {
			System.out.println("no solution");
			return;
		}

		for (int r = 0; r < solution.length; r++) {
			for (int c = 0; c < solution.length; c++) {
				System.out.printf("%s ", solution[r][c]);
			}
			System.out.println();
		}
	}

	private static boolean backtrack(int r, int c, int steps, int[][] solution, int[] xMoves, int[] yMoves, int n) {
		if (steps == n * n)
			return true;

		for (int i = 0; i < 8; i++) {
			int next_r = r + xMoves[i];
			int next_c = c + yMoves[i];

			if (isValidMove(next_r, next_c, solution, n)) {
				solution[next_r][next_c] = steps;
				if (backtrack(next_r, next_c, steps + 1, solution, xMoves, yMoves, n))
					return true;
				else
					solution[next_r][next_c] = -1;
			}
		}
		return false;
	}

	private static boolean isValidMove(int x, int y, int[][] solution, int n) {
		return (x >= 0 && x < n && y >= 0 && y < n && solution[x][y] == -1);
	}
}
