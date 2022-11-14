package tree.binary.traversal;

import tree.binary.MyTreeNode;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BoundaryTraversal {
    public static void main(String[] args) {
        /*-
          Sample tree
                        15
                      /    \
                    10      20
                   /  \    /  \
                  8   12  16   25
                     /      \
                    7       90
        */
        MyTreeNode root = new MyTreeNode(15);
        root.left = new MyTreeNode(10);
        root.right = new MyTreeNode(20);

        root.left.left = new MyTreeNode(8);
        root.left.right = new MyTreeNode(12);
        root.left.right.left = new MyTreeNode(7);

        root.right.left = new MyTreeNode(16);
        root.right.right = new MyTreeNode(25);
        root.right.left.right = new MyTreeNode(90);

        BoundaryTraversal problem = new BoundaryTraversal();
        String res = "Recursive (DFS) T(n) = O(n) : " + problem.boundaryTraversalR(root) + "\n" +
                "Iterative (BFS) T(n) = O(n) : " + problem.boundaryTraversalI(root);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String boundaryTraversalI(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result.toString();

        //add the root node
        if (root.left != null && root.right != null)
            result.add(root.val);

        //left boundary
        MyTreeNode c = root.left;
        while (c != null) {
            //add non-leaf nodes
            if (c.left != null && c.right != null)
                result.add(c.val);

            if (c.left == null)
                c = c.right;
            else
                c = c.left;
        }

        //leafs
        leafsR(root, result);

        //right boundary
        Stack<Integer> s = new Stack<>();
        c = root.right;
        while (c != null) {
            //add non-leaf nodes
            if (c.left != null && c.right != null)
                s.push(c.val);

            if (c.right == null)
                c = c.left;
            else
                c = c.right;
        }

        while (!s.empty())
            result.add(s.pop());

        return result.toString();
    }

    private String boundaryTraversalR(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result.toString();

        //print the root
        result.add(root.val);

        //left boundary, top-down fashion;
        leftBoundaryR(root.left, result);

        //leaf nodes
        leafsR(root.left, result);
        leafsR(root.right, result);

        //right boundary, bottom-up
        rightBoundaryR(root.right, result);

        return result.toString();
    }

    private void leftBoundaryR(MyTreeNode root, List<Integer> traversal) {
        if (root == null || (root.left == null && root.right == null))
            return;

        traversal.add(root.val);

        if (root.left == null)
            leftBoundaryR(root.right, traversal);
        else
            leftBoundaryR(root.left, traversal);
    }

    private void rightBoundaryR(MyTreeNode root, List<Integer> traversal) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.right == null)
            rightBoundaryR(root.left, traversal);
        else
            rightBoundaryR(root.right, traversal);

        traversal.add(root.val);
    }

    private void leafsR(MyTreeNode root, List<Integer> traversal) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            traversal.add(root.val);
            return;
        }

        leafsR(root.left, traversal);
        leafsR(root.right, traversal);
    }
}