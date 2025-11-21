package graph.topological;

import java.io.PrintWriter;
import java.util.*;

class ParallelCourses {
    /*-
    You are given an integer n, which indicates that there are n courses labeled from 1 to n.
    You are also given an array relations where relations[i] = [prevCourse(i), nextCourse(i)], representing a prerequisite relationship between course prevCourse(i) and course nextCourse(i): course prevCourse(i) has to be taken before course nextCourse(i).

    In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.

    Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.

    Examples
        Input: n = 3, relations = [[1,3],[2,3]]
        Output: 2
        Explanation: The figure above represents the given graph.
        In the first semester, you can take courses 1 and 2.
        In the second semester, you can take course 3.

        Input: n = 3, relations = [[1,2],[2,3],[3,1]]
        Output: -1
        Explanation: No course can be studied because they are prerequisites of each other.
     */
    public static void main(String[] args) {
        int n = 3;
        int[][] relations = {{1, 3}, {2, 3}};

        StringBuilder gridRep = new StringBuilder();
        for (int[] r : relations) {
            gridRep.append(Arrays.toString(r)).append("\n");
        }

        ParallelCourses problem = new ParallelCourses();

        String result =
                "#semesters required to complete " + n + " courses with relations between the courses" + "\n" +
                        gridRep + "\n" +
                        "Using Kahn Algorithm + BFS (level-tracking) T(n) = O(V+E) S(n) = O(V+E) : " + problem.minimumSemesters(n, relations);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(result);
        pw.close();
    }

    private int minimumSemesters(int n, int[][] relations) {
        int semesterCount = 0;
        int[] inDegree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> order = new ArrayList<>();
        Queue<Integer> bfsQueue = new LinkedList<>();

        Arrays.fill(inDegree, 0);
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());


        for (int[] edge : relations) {
            graph.get(edge[0] - 1).add(edge[1] - 1);
            inDegree[edge[1] - 1] += 1;
        }

        for (int v = 0; v < n; v += 1)
            if (inDegree[v] == 0)
                bfsQueue.add(v);

        while (!bfsQueue.isEmpty()) {
            int levelSize = bfsQueue.size();
            semesterCount += 1;

            for (int l = 0; l < levelSize; l += 1) {
                int u = bfsQueue.poll();

                order.add(u);

                for (int v : graph.get(u)) {
                    inDegree[v] -= 1;

                    if (inDegree[v] == 0)
                        bfsQueue.offer(v);
                }
            }
        }

        return order.size() == n ? semesterCount : -1;
    }
}
