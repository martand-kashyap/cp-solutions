package famousproblems;

import java.io.PrintWriter;

class GasStation {
    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};

        GasStation gs = new GasStation();
        String res = "" + gs.bruteforce(gas, cost);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int[] gas, int[] cost) {
        int n = gas.length, possibleStart = -1;

        for (int startPoint = 0; startPoint < n; startPoint += 1) {
            for (int current = startPoint; current <= startPoint; current = (current + 1) % n) {
                int gasRequired = cost[current];
                int gasAvailable = gas[current];

                if (gasAvailable - gasRequired < 0) {
                    continue;
                } else {
                    possibleStart = startPoint;
                }
            }
        }

        return possibleStart;
    }
}
