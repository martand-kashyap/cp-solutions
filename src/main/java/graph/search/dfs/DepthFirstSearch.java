package graph.search.dfs;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import graph.ProcessingStatus;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;

@SuppressWarnings("UnstableApiUsage")
class DepthFirstSearch {
    public static void main(String[] args) {
        int numOfVertices = 7, source = 0;

        MutableGraph<Integer> graph = GraphBuilder.undirected().build();

        //add vertices
        for (int i = 0; i < numOfVertices; i += 1)
            graph.addNode(i);

        //add edges
        graph.putEdge(0, 1);
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
        graph.putEdge(6, 5);

        StringBuilder graphRep = new StringBuilder();
        for (int i = 0; i < numOfVertices; i++)
            graphRep.append(i).append(" : ").append(graph.successors(i)).append("\n");

        DepthFirstSearch problem = new DepthFirstSearch();

        String res =
                "DFS traversal of the graph : " + "\n" +
                        graphRep + "\n" +
                        "Recursive T(n) = O(V+E), S(n) = O(1) : " + problem.dfsR(graph, numOfVertices, source) + "\n" +
                        "Recursive [3 colors] T(n) = O(V+E), S(n) = O(1) : " + problem.dfsColorsR(graph, numOfVertices, source) + "\n" +
                        "Iterative T(n) = O(V+E), S(n) = O(V) : " + problem.dfsI(graph, numOfVertices, source);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String dfsR(MutableGraph<Integer> graph, int vertexCount, int source) {
        boolean[] visited = new boolean[vertexCount];
        StringBuilder dfsSequence = new StringBuilder();

        /*Traverser.forGraph(graph).depthFirstPreOrder(source)
                .forEach(dfsNode -> dfsSequence.append(dfsNode).append("->"));*/

        dfsUtil(graph, source, visited, dfsSequence);

        return dfsSequence.toString();
    }

    private void dfsUtil(MutableGraph<Integer> graph, int u, boolean[] visited, StringBuilder dfsSequence) {
        visited[u] = true;
        dfsSequence.append(u).append("->");

        for (int v : graph.successors(u))
            if (!visited[v])
                dfsUtil(graph, v, visited, dfsSequence);
    }

    private String dfsColorsR(MutableGraph<Integer> graph, int numOfVertices, int source) {
        ProcessingStatus[] visited = new ProcessingStatus[numOfVertices];
        Arrays.fill(visited, ProcessingStatus.UNPROCESSED);

        StringBuilder dfsSequence = new StringBuilder();

        /*Traverser.forGraph(graph).depthFirstPreOrder(source)
                .forEach(dfsNode -> dfsSequence.append(dfsNode).append("->"));*/

        dfsColorsUtil(graph, source, visited, dfsSequence);

        return dfsSequence.toString();
    }

    private void dfsColorsUtil(MutableGraph<Integer> graph, int u, ProcessingStatus[] visited, StringBuilder dfsSequence) {
        visited[u] = ProcessingStatus.PROCESSING;
        dfsSequence.append(u).append("->");

        for (int v : graph.successors(u))
            switch (visited[v]) {
                case UNPROCESSED:
                    dfsColorsUtil(graph, v, visited, dfsSequence);
                    break;
                case PROCESSING:
                    break;
            }

        visited[u] = ProcessingStatus.PROCESSED;
    }

    private String dfsI(MutableGraph<Integer> graph, int vertexCount, int source) {
        boolean[] visited = new boolean[vertexCount];
        StringBuilder dfsSequence = new StringBuilder();
        Stack<Integer> dfsStack = new Stack<>();

        dfsStack.add(source);
        while (!dfsStack.isEmpty()) {
            int u = dfsStack.pop();

            if (visited[u])
                continue;

            visited[u] = true;
            dfsSequence.append(u).append("->");

            for (int v : graph.successors(u))
                if (!visited[v])
                    dfsStack.push(v);
        }

        return dfsSequence.toString();
    }
}
