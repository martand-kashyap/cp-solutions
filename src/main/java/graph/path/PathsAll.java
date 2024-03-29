package graph.path;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("UnstableApiUsage")
class PathsAll {
    public static void main(String[] args) {
        int numOfVertices = 5, source = 0, destination = numOfVertices - 1;

        MutableGraph<Integer> graph = GraphBuilder.undirected().build();

        //add vertices
        for (int i = 0; i < numOfVertices; i += 1)
            graph.addNode(i);

        //add edges
        graph.putEdge(0, 4);
        graph.putEdge(0, 3);
        graph.putEdge(0, 1);
        graph.putEdge(1, 3);
        graph.putEdge(1, 2);
        graph.putEdge(1, 4);
        graph.putEdge(2, 3);
        graph.putEdge(3, 4);

        StringBuilder graphRep = new StringBuilder();
        for (int i = 0; i < numOfVertices; i++)
            graphRep.append(i).append(" : ").append(graph.successors(i)).append("\n");

        PathsAll problem = new PathsAll();

        String res =
                "All paths from " + source + " to " + destination + " in the graph : " + "\n" +
                        graphRep + "\n" +
                        "DFS approach T(n) = O(2^V.V), S(n) = O(V) : " + problem.solveDFS(graph, numOfVertices, source, destination) + "\n" +
                        "BFS approach T(n) = O(2^V.V), S(n) = O(V) : " + problem.solveBFS(graph, source, destination);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private List<List<Integer>> solveDFS(MutableGraph<Integer> graph, int numOfVertices, int source, int destination) {
        List<List<Integer>> allPaths = new ArrayList<>();

        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(source);

        boolean[] visited = new boolean[numOfVertices];
        Arrays.fill(visited, false);

        dfs(graph, visited, source, destination, allPaths, currentPath);

        return allPaths;
    }

    private void dfs(MutableGraph<Integer> graph, boolean[] visited, int u, int destination, List<List<Integer>> allPaths, List<Integer> currentPath) {
        if (u == destination) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }

        if (!visited[u]) {
            visited[u] = true;

            for (int nextNodes : graph.successors(u)) {
                currentPath.add(nextNodes);
                dfs(graph, visited, nextNodes, destination, allPaths, currentPath);
                currentPath.remove(currentPath.size() - 1);
            }

            visited[u] = false;
        }
    }

    private List<List<Integer>> solveBFS(MutableGraph<Integer> graph, int source, int destination) {
        List<List<Integer>> allPaths = new ArrayList<>();

        //queue to store the explored paths
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> srcPath = new ArrayList<>();
        srcPath.add(source);
        queue.offer(srcPath);

        while (!queue.isEmpty()) {
            List<Integer> currPath = queue.peek();

            //get the last node on the current path
            int u = currPath.get(currPath.size() - 1);

            if (u == destination)
                allPaths.add(new ArrayList<>(currPath));

            for (int v : graph.successors(u)) {
                if (!currPath.contains(v)) {
                    List<Integer> newPath = new ArrayList<>(currPath);
                    newPath.add(v);
                    queue.add(new ArrayList<>(newPath));
                }
            }
            queue.poll();
        }

        return allPaths;
    }
}
