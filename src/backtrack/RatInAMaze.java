package backtrack;

// Java implementation of the above approach
import java.util.Vector;

class RatInAMaze {

	// Vector to store all the possible paths
	static Vector<String> possiblePaths = new Vector<>();	
	static final int MAX = 5;

// Function returns true if the
// move taken is valid else
// it will return false.
	static boolean isSafe(int row, int col, int m[][], int n, boolean visited[][]) {
		if (row == -1 || row == n || col == -1 || col == n || visited[row][col] || m[row][col] == 0)
			return false;

		return true;
	}

// Function to print all the possible
// paths from (0, 0) to (n-1, n-1).
	static void printPathUtil(int row, int col, int m[][], int n, boolean visited[][], String path) {

		// This will check the initial point
		// (i.e. (0, 0)) to start the paths.
		if (row == -1 || row == n || col == -1 || col == n || visited[row][col] || m[row][col] == 0)
			return;

		// If reach the last cell (n-1, n-1)
		// then store the path and return
		if (row == n - 1 && col == n - 1 && m[row][col]==1) {
			possiblePaths.add(path);
			return;
		}

		// Mark the cell as visited
		visited[row][col] = true;

		// Try for all the 4 directions (down, left,
		// right, up) in the given order to get the
		// paths in lexicographical order

		// Check if downward move is valid
		if (isSafe(row + 1, col, m, n, visited)) {
			path += 'D';
			printPathUtil(row + 1, col, m, n, visited, path);
			path = path.substring(0, path.length() - 1);
		}

		// Check if the left move is valid
		if (isSafe(row, col - 1, m, n, visited)) {
			path += 'L';
			printPathUtil(row, col - 1, m, n, visited, path);
			path = path.substring(0, path.length() - 1);
		}

		// Check if the right move is valid
		if (isSafe(row, col + 1, m, n, visited)) {
			path += 'R';
			printPathUtil(row, col + 1, m, n, visited, path);
			path = path.substring(0, path.length() - 1);
		}

		// Check if the upper move is valid
		if (isSafe(row - 1, col, m, n, visited)) {
			path += 'U';
			printPathUtil(row - 1, col, m, n, visited, path);
			path = path.substring(0, path.length() - 1);
		}
		visited[row][col] = false;
	}
	
	static void printPath(int m[][], int n) {
		boolean[][] visited = new boolean[n][MAX];

		// Call the utility function to
		// find the valid paths
		printPathUtil(0, 0, m, n, visited, "");
		
		System.out.println(possiblePaths);
	}
	
	public static void main(String[] args) {
		int m[][] = { 
						{ 1, 0, 0, 0, 0 },
						{ 1, 1, 0, 0, 0 }, 
						{ 0, 1, 1, 0, 0 }, 
						{ 0, 0, 1, 1, 0 }, 
						{ 0, 0, 1, 1, 1 } };
		int n = m.length;		
		printPath(m, n);
	}
}