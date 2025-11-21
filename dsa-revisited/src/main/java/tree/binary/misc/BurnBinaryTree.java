package tree.binary.misc;

import tree.binary.MyTreeNode;

import java.io.PrintWriter;
import java.util.*;

class BurnBinaryTree {
    /*-
    Given a binary tree and a node data called target,
    find the minimum time required to burn the complete binary tree if the target is set on fire.

    It is known that in 1 second all nodes connected to a given node get burned.
    That is its left child, right child, and parent.

    Note: The tree contains unique values.

    Example 1:
        Input:
                  1
                /   \
              2      3
            /  \      \
           4    5      6
               / \      \
              7   8      9
                           \
                           10
        Target Node = 8
        Output: 7
        Explanation:
        If leaf with the value 8 is set on fire.
        After 1 sec: 5 is set on fire.
        After 2 sec: 2, 7 are set to fire.
        After 3 sec: 4, 1 are set to fire.
        After 4 sec: 3 is set to fire.
        After 5 sec: 6 is set to fire.
        After 6 sec: 9 is set to fire.
        After 7 sec: 10 is set to fire.
        It takes 7s to burn the complete tree.

    Example 2:
        Input:
                  1
                /   \
              2      3
            /  \      \
           4    5      7
          /    /
         8    10
        Target Node = 10
        Output: 5
     */
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(1);

        root.left = new MyTreeNode(2);
        root.right = new MyTreeNode(3);

        root.left.left = new MyTreeNode(4);
        root.left.right = new MyTreeNode(5);
        root.right.right = new MyTreeNode(6);

        root.left.right.left = new MyTreeNode(7);
        root.left.right.right = new MyTreeNode(8);
        root.right.right.right = new MyTreeNode(9);

        root.right.right.right.right = new MyTreeNode(10);

        int target = 8;

        BurnBinaryTree problem = new BurnBinaryTree();
        String res = String.valueOf(problem.solveI(root, target));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int solveI(MyTreeNode root, int key) {
        if (root == null) return -1;

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
        int timeTaken = -1;

        while (!q.isEmpty()) {
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
            timeTaken += 1;
        }

        return timeTaken;
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
