package stack;

import java.io.PrintWriter;

class LargestAreaUnderHistogram2D {
    public static void main(String[] args) {
        int[][] arr = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 0}};
        LargestAreaUnderHistogram2D lauh = new LargestAreaUnderHistogram2D();
        String res = "The maximum area of a rectangle formed only of 1s in the given matrix: " + lauh.getMaxArea(arr);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int getMaxArea(int[][] arr) {
        LargestAreaUnderHistogram h = new LargestAreaUnderHistogram();
        int r = arr.length, c = arr[0].length, maxArea = Integer.MIN_VALUE;
        int[] compressed2D = new int[c];

        // compress the 1st row
        for (int j = 0; j < c; j += 1)
            compressed2D[j] = arr[0][j];
        // solve for the first row only
        maxArea = h.getMaxAreaUnderHistogram(compressed2D);

        for (int i = 1; i < r; i += 1) {
            // add each row & solve each time
            for (int j = 0; j < c; j += 1) {
                if (arr[i][j] == 0) compressed2D[j] = 0;
                else compressed2D[j] += arr[i][j];
            }
            int maxInRow = h.getMaxAreaUnderHistogram(compressed2D);
            maxArea = Integer.max(maxArea, maxInRow);
        }
        return maxArea;
    }
}
