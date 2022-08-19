package heap;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

class KClosestPointsFromOrigin {
    public static void main(String[] args) {
        int[][] points = { { 1, 0 }, { 2, 1 }, { 3, 6 }, { -5, 2 }, { 1, -4 } };
        int k = 3, n = points.length;

        KClosestPointsFromOrigin kcpo = new KClosestPointsFromOrigin();
        int[][] closestPoints = kcpo.getKClosestPointsFromOrigin(points, n, k);

        StringBuffer res = new StringBuffer("K = " + k + " closest points from (0, 0)\n");
        for (int[] point : closestPoints) {
            res.append(Arrays.toString(point) + "\n");
        }

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int[][] getKClosestPointsFromOrigin(int[][] points, int n, int k) {
        int[][] result = new int[k][2];
        Point[] pointObj = new Point[n];
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k,
                (p1, p2) -> p2.distanceFromOrigin() - p1.distanceFromOrigin());

        for (int i = 0; i < n; i += 1)
            pointObj[i] = new Point(points[i][0], points[i][1]);

        for (Point p : pointObj) {
            maxHeap.offer(p);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        for (int i = 0; !maxHeap.isEmpty(); i += 1) {
            Point p = maxHeap.poll();
            result[i][0] = p.x;
            result[i][1] = p.y;
        }

        return result;
    }
}
