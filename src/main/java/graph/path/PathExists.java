package graph.path;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.io.PrintWriter;
import java.util.Arrays;

@SuppressWarnings("UnstableApiUsage")
class PathExists {
    public static void main(String[] args) {
        int numOfVertices = 6, source = 0, destination = 5;

        MutableGraph<Integer> graph = GraphBuilder.undirected().build();

        //add vertices
        for (int i = 0; i < numOfVertices; i += 1)
            graph.addNode(i);

        //add edges
        graph.putEdge(0, 1);
        graph.putEdge(0, 2);
        graph.putEdge(3, 5);
        graph.putEdge(5, 4);
        graph.putEdge(4, 3);

        StringBuilder graphRep = new StringBuilder();
        for (int i = 0; i < numOfVertices; i++)
            graphRep.append(i).append(" : ").append(graph.successors(i)).append("\n");

        PathExists problem = new PathExists();

        String res =
                "A path from " + source + " to " + destination + " in the graph : " + "\n" +
                        graphRep + "\n" +
                        "DFS approach T(n) = O(V+E), S(n) = O(V) : " + (problem.solveDFS(graph, numOfVertices, source, destination) ? " exists." : " does not exist.");

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean solveDFS(MutableGraph<Integer> graph, int numOfVertices, int source, int destination) {
        boolean[] visited = new boolean[numOfVertices];
        Arrays.fill(visited, false);

        return dfs(graph, visited, source, destination);
    }

    private boolean dfs(MutableGraph<Integer> graph, boolean[] visited, int u, int destination) {
        if (u == destination)
            return true;

        if (!visited[u]) {
            visited[u] = true;

            for (int nextNode : graph.successors(u)) {
                boolean pathFound = dfs(graph, visited, nextNode, destination);
                if (pathFound)
                    return true;
            }
        }

        return false;
    }
}
