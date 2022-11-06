package graph.topological;

import java.io.PrintWriter;
import java.util.*;

/*-
    A tree is an undirected graph in which any two vertices are connected by exactly one path.
    In other words, any connected graph without simple cycles is a tree.

    Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges
    where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree,
    you can choose any node of the tree as the root.
    When you select a node x as the root, the result tree has height h.
    Among all possible rooted trees, those with minimum height (i.e. min(h)) are called minimum height trees (MHTs).

    Return a list of all MHTs' root labels. You can return the answer in any order.
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

    Example
    Input: n = 4, edges = [[1,0],[1,2],[1,3]]
    Output: [1]

    Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
    Output: [3,4]
    */
class MinimumHeightTree {

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};

        StringBuilder gridRep = new StringBuilder();
        for (int[] r : edges) {
            gridRep.append(Arrays.toString(r)).append("\n");
        }

        MinimumHeightTree problem = new MinimumHeightTree();

        String result =
                "The roots which result in minimum height tree from the graph" + "\n" +
                        gridRep + "\n" +
                        /*-
                        Let V be the number of nodes in the graph, then the number of edges would be V−1 (since a tree is given).

                        Time Complexity
                        1. It takes V−1 iterations for us to construct a graph, given the edges.
                        2. With the constructed graph, we retrieve the initial leaf nodes, which takes V steps.
                        3. During the BFS trimming process, we will trim out almost all the nodes (V) and edges (V−1) from the edges.
                           Therefore, it would take us around (V+V−1) operations to reach the solution.

                        To sum up, the overall time complexity of the algorithm is O(V).

                        Space Complexity
                        1. We construct the graph with adjacency list, which has V nodes and V−1 edges.
                           Therefore, we would need (V+V-1) space for the representation of the graph.
                        2. We use a queue to keep track of the leaf nodes.
                           In the worst case, the nodes form a star shape, with one centroid and the rest of the nodes as leaf nodes.
                           In this case, we would need V−1 space for the queue.

                        To sum up, the overall space complexity of the algorithm is also O(V).
                        */
                        "Using modified Kahn Algorithm T(n) = O(V) S(n) = O(V) : " + problem.getMHTRoots(n, edges);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(result);
        pw.close();
    }

    private List<Integer> getMHTRoots(int n, int[][] edges) {
        List<Integer> rootsOfMHTs = new ArrayList<>();

        // We can solve this problem by first thinking about the 1-D solution,
        // that is if the longest graph is given, then the node which will minimize the height
        // will be mid node if total node count is odd
        // or mid-two-node if total node count is even.
        if (n < 2) {
            for (int i = 0; i < n; i++)
                rootsOfMHTs.add(i);

            return rootsOfMHTs;
        }

        int[] inDegree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> bfsQueue = new LinkedList<>();

        Arrays.fill(inDegree, 0);
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);

            inDegree[edge[0]] += 1;
            inDegree[edge[1]] += 1;
        }

        // in-degree(v)=1 => v is a leaf node,
        // add all leaf nodes to Queue
        for (int v = 0; v < n; v += 1)
            if (inDegree[v] == 1)
                bfsQueue.offer(v);

        int remainingNodes = n;
        while (remainingNodes > 2) {
            int levelSize = bfsQueue.size();
            remainingNodes -= levelSize;

            for (int l = 0; l < levelSize; l++) {
                int u = bfsQueue.poll();
                inDegree[u] -= 1;

                for (int v : graph.get(u)) {
                    inDegree[v] -= 1;
                    if (inDegree[v] == 1)
                        bfsQueue.offer(v);
                }
            }
        }

        //whatever is left in the queue, are possible roots which result in MHT.
        while (!bfsQueue.isEmpty())
            rootsOfMHTs.add(bfsQueue.poll());

        return rootsOfMHTs;
    }
}
