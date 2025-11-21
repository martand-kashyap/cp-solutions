package search.binary;

class SearchIn2DSortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 16;

        System.out.println(searchMatrix(matrix, target));
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;

        int i = 0, j = c - 1;

        while (i < r && j >= 0) {
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] > target)
                j -= 1;
            else
                i += 1;
        }

        return false;
    }
}
