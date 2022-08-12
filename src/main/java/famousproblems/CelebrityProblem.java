package famousproblems;

import java.io.PrintWriter;
import java.util.Stack;

class CelebrityProblem {
    public static void main(String[] args) {
        int[][] inp = {{0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0}};

        CelebrityProblem cp = new CelebrityProblem();
        String res = "Result (2 Pointers) : " + cp.using2Pointers(inp)
                + "\nResult (Stack) : " + cp.usingStack(inp);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int bruteforce(int[][] knows) {
        return -1;
    }

    private int usingStack(int[][] knows) {
        Stack<Integer> stack = new Stack<>();
        int candidate;

        for (int i = 0; i < knows.length; i += 1) {
            stack.push(i);
        }

        while (stack.size() > 1) {
            int p1 = stack.pop();
            int p2 = stack.pop();

            if (knows[p1][p2] == 1) {
                stack.push(p2);
            } else {
                stack.push(p1);
            }
        }

        if (stack.empty()) {
            return -1;
        }

        candidate = stack.pop();

        for (int person = 0; person < knows.length; person += 1) {
            if (person == candidate) {
                continue;
            }

            // person should a some other candidate
            if (knows[candidate][person] == 1 || knows[person][candidate] == 0) {
                return -1;
            }
        }

        return candidate;
    }

    private int using2Pointers(int[][] knows) {
        int n = knows.length;
        int l = 0, r = n - 1;

        while (l < r) {
            if (knows[l][r] == 1) {
                // person[l] cannot be celebrity 'cuz l knows j
                l++;
            } else {
                r--;
            }
        }

        int candidate = l;

        for (int person = 0; person < n; person += 1) {
            if (person == candidate) {
                continue;
            }

            if (knows[candidate][person] == 1 || knows[person][candidate] == 0) {
                return -1;
            }
        }

        return candidate;
    }
}
