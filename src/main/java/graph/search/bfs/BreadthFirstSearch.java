package graph.search.bfs;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.Traverser;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("UnstableApiUsage")
class BreadthFirstSearch {
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
            graphRep.append(i).append(" : ").append(graph.adjacentNodes(i)).append("\n");

        BreadthFirstSearch problem = new BreadthFirstSearch();

        String res =
                "BFS traversal of the graph : " + "\n" +
                        graphRep + "\n" +
                        "Iterative T(n) = O(V+E), S(n) = O(V) : " + problem.bfsI(graph, numOfVertices, source);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String bfsI(MutableGraph<Integer> graph, int numOfVertices, int source) {
        boolean[] visited = new boolean[numOfVertices];
        Queue<Integer> bfsQueue = new LinkedList<>();
        StringBuilder bfsSequence = new StringBuilder();

        /*Traverser.forGraph(graph).breadthFirst(source)
                .forEach(bfsNode -> bfsSequence.append(bfsNode).append("->"));*/

        bfsQueue.add(source);
        while (!bfsQueue.isEmpty()) {
            int u = bfsQueue.poll();

            if (visited[u])
                continue;

            visited[u] = true;
            bfsSequence.append(u).append("->");

            for (int v : graph.adjacentNodes(u))
                if (!visited[v])
                    bfsQueue.offer(v);
        }

        return bfsSequence.toString();
    }
}
