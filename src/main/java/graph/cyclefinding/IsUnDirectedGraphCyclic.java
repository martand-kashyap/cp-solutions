package graph.cyclefinding;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import graph.ProcessingStatus;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("UnstableApiUsage")
class IsUnDirectedGraphCyclic {
    public static void main(String[] args) {
        int numOfVertices = 5;
        // int numOfVertices = 7;

        MutableGraph<Integer> graph = GraphBuilder.undirected().build();

        // add vertices
        for (int i = 0; i < numOfVertices; i += 1)
            graph.addNode(i);

        // add edges
        graph.putEdge(0, 4);
        graph.putEdge(1, 2);
        graph.putEdge(1, 4);
        graph.putEdge(2, 3);
        graph.putEdge(3, 4);

        /*
         * graph.putEdge(0, 1);
         * graph.putEdge(0, 2);
         * graph.putEdge(1, 0);
         * graph.putEdge(1, 2);
         * graph.putEdge(1, 3);
         * graph.putEdge(2, 0);
         * graph.putEdge(2, 1);
         * graph.putEdge(2, 4);
         * graph.putEdge(3, 1);
         * graph.putEdge(3, 4);
         * graph.putEdge(4, 2);
         * graph.putEdge(4, 3);
         * graph.putEdge(4, 5);
         * graph.putEdge(5, 4);
         * graph.putEdge(5, 6);
         * graph.putEdge(6, 5);
         */

        StringBuilder graphRep = new StringBuilder();
        for (int i = 0; i < numOfVertices; i++)
            graphRep.append(i).append(" : ").append(graph.successors(i)).append("\n");

        IsUnDirectedGraphCyclic problem = new IsUnDirectedGraphCyclic();

        String res = "The undirected graph : " + "\n" +
                graphRep + "\n" +
                "DFS recursive \nT(n) = O(V+E), S(n) = O(1) : " + (problem.dfsR(graph, numOfVertices) ? "has a cycle" : "does not have a cycle") + "\n\n" +
                "DFS recursive [3 colors impl] \nT(n) = O(V+E), S(n) = O(1) : " + (problem.dfsColorsR(graph, numOfVertices) ? "has a cycle" : "does not have a cycle") + "\n\n" +
                "BFS \nT(n) = O(V+E), S(n) = O(V) : " + (problem.bfs(graph) ? "has a cycle" : "does not have a cycle") + "\n\n" +
                "BFS Alternative Impl\nT(n) = O(V+E), S(n) = O(V) : " + (problem.bfsAlt(graph) ? "has a cycle" : "does not have a cycle");
        
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean dfsColorsR(MutableGraph<Integer> graph, int numOfVertices) {
        ProcessingStatus[] visited = new ProcessingStatus[numOfVertices];
        Arrays.fill(visited, ProcessingStatus.UNPROCESSED);

        for (int u : graph.nodes())
            if (visited[u] == ProcessingStatus.UNPROCESSED && dfsColorsUtil(graph, u, visited, -1))
                return true;

        return false;
    }

    private boolean dfsColorsUtil(MutableGraph<Integer> graph, int u, ProcessingStatus[] visited, int parent) {
        visited[u] = ProcessingStatus.PROCESSING;

        for (int v : graph.successors(u)) {
            if (v == parent)
                continue;
            switch (visited[v]) {
                case PROCESSING:
                    return true;
                case UNPROCESSED:
                    if (dfsColorsUtil(graph, v, visited, u))
                        return true;
                    break;
            }
        }

        visited[u] = ProcessingStatus.PROCESSED;
        return false;
    }

    private boolean dfsR(MutableGraph<Integer> graph, int numOfVertices) {
        boolean[] visited = new boolean[numOfVertices];

        for (int u : graph.nodes())
            if (!visited[u] && dfsUtil(graph, u, visited, -1))
                return true;

        return false;
    }

    private boolean dfsUtil(MutableGraph<Integer> graph, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int v : graph.successors(u)) {
            if (v == parent) {
                continue;
            }
            if (visited[v]) {
                return true;
            } else if (dfsUtil(graph, v, visited, u)) {
                return true;
            }
        }

        return false;
    }

    private boolean bfs(MutableGraph<Integer> graph) {
        boolean[] visited = new boolean[graph.nodes().size()];

        for (int u : graph.nodes())
            if (!visited[u] && bfsUtil(graph, u, visited)) {
                return true;
            }

        return false;
    }

    private boolean bfsUtil(MutableGraph<Integer> graph, int source, boolean[] visited) {
        int[] ancestors = new int[graph.nodes().size()];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(ancestors, -1);

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : graph.successors(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                    ancestors[v] = u;
                } else if (ancestors[u] != v) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean bfsAlt(MutableGraph<Integer> graph) {
        boolean[] visited = new boolean[graph.nodes().size()];

        for (int u : graph.nodes())
            if (!visited[u] && bfsAltUtil(graph, u, visited)) {
                return true;
            }

        return false;
    }

    private boolean bfsAltUtil(MutableGraph<Integer> graph, int source, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            if (visited[u])
                return true;

            visited[u] = true;
            for (int v : graph.successors(u))
                if (!visited[v])
                    queue.offer(v);
        }

        return false;
    }
}
