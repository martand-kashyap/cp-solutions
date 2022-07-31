package search.binary;

import java.io.PrintWriter;

class FloorSquareRoot {
    public static void main(String[] args) {
        int x = 17;
        FloorSquareRoot sr = new FloorSquareRoot();

        String res = "floor(sqrt(" + x + ")) : " + sr.floorSqrt(x);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);

        pw.close();
    }

    private int floorSqrt(int x) {
        if (x == 0 || x == 1) return x;

        long l = 1, r = x / 2;
        long res = 0;
        while (l <= r) {
            long m = l + (r - l) / 2;
            long squareX = m * m;

            if (squareX == x) {
                return (int) m;
            } else if (squareX < x) {
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return (int) res;
    }
}
