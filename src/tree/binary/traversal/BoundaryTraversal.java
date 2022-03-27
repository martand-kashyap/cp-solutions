package tree.binary.traversal;

import tree.binary.MyTreeNode;

import java.util.ArrayList;
import java.util.Stack;

class BoundaryTraversal {
    public static void main(String[] args) {
        /**
         * Sample tree
         *               15
         *             /    \
         *           10      20
         *          /  \    /  \
         *         8   12  16   25
         *            /      \
         *           7       90
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

        ArrayList<Integer> traversal = new ArrayList<>();
        boundaryTraversalR(root, traversal);
        System.out.println("Recursive: " + traversal);

        traversal.clear();
        boundaryTraversalI(root, traversal);
        System.out.println("Iterative: " + traversal);
    }

    private static void boundaryTraversalI(MyTreeNode root, ArrayList<Integer> traversal) {
        if (root == null)
            return;

        //add the root node
        if (root.left != null && root.right != null)
            traversal.add(root.val);

        //left boundary
        MyTreeNode c = root.left;
        while (c != null) {
            //add non-leaf nodes
            if (c.left != null && c.right != null)
                traversal.add(c.val);

            if (c.left == null)
                c = c.right;
            else
                c = c.left;
        }

        //leafs
        leafsR(root, traversal);

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
            traversal.add(s.pop());
    }

    private static void boundaryTraversalR(MyTreeNode root, ArrayList<Integer> traversal) {
        if (root == null)
            return;

        //print the root
        traversal.add(root.val);

        //left boundary, top-down fashion;
        leftBoundaryR(root.left, traversal);

        //leaf nodes
        leafsR(root.left, traversal);
        leafsR(root.right, traversal);

        //right boundary, bottom-up
        rightBoundaryR(root.right, traversal);
    }

    private static void leftBoundaryR(MyTreeNode root, ArrayList<Integer> traversal) {
        if (root == null || (root.left == null && root.right == null))
            return;

        traversal.add(root.val);

        if (root.left == null)
            leftBoundaryR(root.right, traversal);
        else
            leftBoundaryR(root.left, traversal);
    }

    private static void rightBoundaryR(MyTreeNode root, ArrayList<Integer> traversal) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.right == null)
            rightBoundaryR(root.left, traversal);
        else
            rightBoundaryR(root.right, traversal);

        traversal.add(root.val);
    }

    private static void leafsR(MyTreeNode root, ArrayList<Integer> traversal) {
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