package graph.topological;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("UnstableApiUsage")
class TopologicalSort {
    public static void main(String[] args) {
        int numOfVertices = 5;

        MutableGraph<Integer> graph = GraphBuilder.directed().build();

        for (int i = 0; i < numOfVertices; i++)
            graph.addNode(i);

        graph.putEdge(0, 1);
        graph.putEdge(0, 2);
        graph.putEdge(1, 2);
        graph.putEdge(2, 3);
        graph.putEdge(4, 1);

        StringBuilder graphRep = new StringBuilder();
        for (int i = 0; i < numOfVertices; i++)
            graphRep.append(i).append(" : ").append(graph.successors(i)).append("\n");

        TopologicalSort problem = new TopologicalSort();

        String res =
                "Topographical Order of the DAG : " + "\n" +
                        graphRep + "\n" +
                        "DFS (recursive) T(n) = O(V+E), S(n) = O(V) : " + problem.topographicalSortUsingDFSR(graph, numOfVertices) + "\n" +
                        "Kahn's Algorithm (iterative) T(n) = O(V+E), S(n) = O(E) : " + problem.kahnAlgorithm(graph, numOfVertices) + "\n" +
                        "Departure Time of Vertex T(n) = O(V+E), S(n) = O(E) : " + problem.topographicalSortUsingDepartureTime(graph, numOfVertices);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private List<Integer> topographicalSortUsingDFSR(MutableGraph<Integer> graph, int numOfVertices) {
        List<Integer> topographicalOrder = new ArrayList<>();
        Set<Integer> nodesVisited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        //Run O(E) procedure for each node => O(V+E)
        for (int u = 0; u < numOfVertices; u++)
            if (graph.inDegree(u) == 0 && !nodesVisited.contains(u))
                dfsUtilR(graph, nodesVisited, stack, u);

        //O(V) space complexity
        while (!stack.empty())
            topographicalOrder.add(stack.pop());

        return topographicalOrder;
    }

    /*-
    Runtime = O(E) for 1 vertex
     */
    private void dfsUtilR(MutableGraph<Integer> graph, Set<Integer> nodesVisited, Stack<Integer> stack, int source) {
        nodesVisited.add(source);

        for (int v : graph.successors(source))
            if (!nodesVisited.contains(v))
                dfsUtilR(graph, nodesVisited, stack, v);

        stack.push(source);
    }

    private List<Integer> kahnAlgorithm(MutableGraph<Integer> graph, int numOfVertices) {
        List<Integer> topographicalOrder = new ArrayList<>();
        Map<Integer, Integer> vertexInDegrees = new HashMap<>();
        Queue<Integer> bfsQueue = new LinkedList<>();

        for (int vertex : graph.nodes()) {
            vertexInDegrees.put(vertex, graph.inDegree(vertex));
        }

        for (int vertex : graph.nodes()) {
            if (vertexInDegrees.get(vertex) == 0) {
                bfsQueue.offer(vertex);
            }
        }

        while (!bfsQueue.isEmpty()) {
            int u = bfsQueue.poll();

            topographicalOrder.add(u);

            for (int v : graph.successors(u)) {
                vertexInDegrees.put(v, vertexInDegrees.get(v) - 1);
                if (vertexInDegrees.get(v) == 0) {
                    bfsQueue.offer(v);
                }
            }
        }

        return topographicalOrder.size() == numOfVertices ? topographicalOrder : null;
    }

    private List<Integer> topographicalSortUsingDepartureTime(MutableGraph<Integer> graph, int numOfVertices) {
        return null;
    }
}
