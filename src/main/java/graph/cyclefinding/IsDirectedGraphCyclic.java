package graph.cyclefinding;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import graph.ProcessingStatus;

import java.io.PrintWriter;
import java.util.Arrays;

@SuppressWarnings("UnstableApiUsage")
class IsDirectedGraphCyclic {
    public static void main(String[] args) {
        int numOfVertices = 4;
        //int numOfVertices = 7;

        MutableGraph<Integer> graph = GraphBuilder.directed().build();

        //add vertices
        for (int i = 0; i < numOfVertices; i += 1)
            graph.addNode(i);

        //add edges
        graph.putEdge(1, 2);
        graph.putEdge(2, 3);

        /*graph.putEdge(0, 1);
        graph.putEdge(0, 2);
        graph.putEdge(1, 0);
        graph.putEdge(1, 2);
        graph.putEdge(1, 3);
        graph.putEdge(2, 0);
        graph.putEdge(2, 1);
        graph.putEdge(2, 4);
        graph.putEdge(3, 1);
        graph.putEdge(3, 4);
        graph.putEdge(4, 2);
        graph.putEdge(4, 3);
        graph.putEdge(4, 5);
        graph.putEdge(5, 4);
        graph.putEdge(5, 6);
        graph.putEdge(6, 5);*/

        StringBuilder graphRep = new StringBuilder();
        for (int i = 0; i < numOfVertices; i++)
            graphRep.append(i).append(" : ").append(graph.successors(i)).append("\n");

        IsDirectedGraphCyclic problem = new IsDirectedGraphCyclic();

        String res =
                "The directed graph : " + "\n" +
                        graphRep + "\n" +
                        "DFS recursive [3 colors] T(n) = O(V+E), S(n) = O(1) : " + (problem.dfsColorsR(graph, numOfVertices) ? "has a cycle" : "does not have a cycle");

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean dfsColorsR(MutableGraph<Integer> graph, int numOfVertices) {
        ProcessingStatus[] visited = new ProcessingStatus[numOfVertices];
        Arrays.fill(visited, ProcessingStatus.UNPROCESSED);

        for (int u : graph.nodes())
            if (visited[u] == ProcessingStatus.UNPROCESSED && dfsColorsUtil(graph, u, visited))
                return true;

        return false;
    }

    private boolean dfsColorsUtil(MutableGraph<Integer> graph, int u, ProcessingStatus[] visited) {
        visited[u] = ProcessingStatus.PROCESSING;

        for (int v : graph.successors(u))
            switch (visited[v]) {
                case PROCESSING:
                    return true;
                case UNPROCESSED:
                    if (dfsColorsUtil(graph, v, visited))
                        return true;
                    break;
            }

        visited[u] = ProcessingStatus.PROCESSED;
        return false;
    }
}
