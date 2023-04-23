package tree.binary.misc;

import tree.binary.MyTreeNode;

import java.io.PrintWriter;
import java.util.*;

class NodesAtDistanceK {
    /*-
    Given a binary tree, a target node in the binary tree, and an integer value k,
    find all the nodes that are at distance k from the given target node.

    No parent pointers are available.
    Note: You have to return list in sorted order.

    Example 1:
    Input:
              20
            /    \
          8       22
        /   \
       4    12
           /   \
          10    14

    Target Node = 8, K = 2
    Output: 10 14 22
    Explanation: The three nodes at distance 2, from node 8 are :- 10, 14, 22.

    Example 2:
    Input:
             20
           /    \
          7      24
        /   \
       4     3
            /
           1
    Target Node = 7, K = 2
    Output: 1 24
     */
    public static void main(String[] args) {
        /*-
        MyTreeNode root = new MyTreeNode(20);

        root.left = new MyTreeNode(8);
        root.right = new MyTreeNode(22);

        root.left.left = new MyTreeNode(4);
        root.left.right = new MyTreeNode(12);

        root.left.right.left = new MyTreeNode(10);
        root.left.right.right = new MyTreeNode(14);

        int target = 7, k = 2;
        */

        MyTreeNode root = new MyTreeNode(20);

        root.left = new MyTreeNode(7);
        root.right = new MyTreeNode(24);

        root.left.left = new MyTreeNode(4);
        root.left.right = new MyTreeNode(3);

        root.left.right.left = new MyTreeNode(1);

        int target = 7, k = 2;

        NodesAtDistanceK problem = new NodesAtDistanceK();
        String res = String.valueOf(problem.solveI(root, target, k));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private List<Integer> solveI(MyTreeNode root, int key, int k) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        //find the ancestors of each nodes, so we can travel through parents
        Map<MyTreeNode, MyTreeNode> nodeParentMap = findParents(root);

        //find the node which has the value = key
        MyTreeNode target = dfs(root, key);

        //level order traversal, with travelling in 3 directions
        //left, right & parent

        //track the nodes which have already been visited
        Set<MyTreeNode> seen = new HashSet<>();
        Queue<MyTreeNode> q = new LinkedList<>();

        //start the level order traversal from the target node,
        //instead of the root node
        q.offer(target);
        seen.add(target);
        int currentDistance = 0;

        while (currentDistance < k) {
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                MyTreeNode c = q.poll();
                MyTreeNode p = nodeParentMap.getOrDefault(c, null);

                if (c.left != null && !seen.contains(c.left)) {
                    seen.add(c.left);
                    q.offer(c.left);
                }
                if (c.right != null && !seen.contains(c.right)) {
                    seen.add(c.right);
                    q.offer(c.right);
                }
                if (p != null && !seen.contains(p)) {
                    seen.add(p);
                    q.offer(p);
                }
            }
            currentDistance += 1;
        }

        while (!q.isEmpty()) {
            MyTreeNode c = q.poll();
            result.add(c.val);
        }

        //sort the nodes in increasing order
        Collections.sort(result);
        
        return result;
    }

    private Map<MyTreeNode, MyTreeNode> findParents(MyTreeNode root) {
        Map<MyTreeNode, MyTreeNode> res = new HashMap<>();
        Queue<MyTreeNode> q = new LinkedList<>();

        q.offer(root);
        res.put(root, null);

        while (!q.isEmpty()) {
            MyTreeNode c = q.poll();

            if (c.left != null) {
                res.put(c.left, c);
                q.offer(c.left);
            }
            if (c.right != null) {
                res.put(c.right, c);
                q.offer(c.right);
            }
        }

        return res;
    }

    private MyTreeNode dfs(MyTreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) return root;

        MyTreeNode l = dfs(root.left, key);
        MyTreeNode r = dfs(root.right, key);

        return l != null ? l : r;
    }
}
