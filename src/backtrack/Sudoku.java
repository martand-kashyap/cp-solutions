package backtrack;

class Sudoku {
    public static void main(String[] args) {
        int[][] inputBoard = {  { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                                { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        int n = 9;

        if(sudokuUtil(inputBoard, 0, 0, n))
            System.out.println(inputBoard);
        else
            System.out.println("no solution");
    }

    private static boolean sudokuUtil(int[][] board, int r, int c, int n) {
        return true;
    }
}
