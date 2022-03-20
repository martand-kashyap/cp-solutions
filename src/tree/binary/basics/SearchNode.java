package tree.binary.basics;

import tree.binary.MyTreeNode;

import java.util.LinkedList;
import java.util.Queue;

class SearchNode {
    public static void main(String[] args) {
        /**
         * Sample tree
         *               15
         *             /    \
         *           10      20
         *          /  \    /  \
         *         8   12  16   25
         */
        MyTreeNode root = new MyTreeNode(15);
        root.left = new MyTreeNode(10);
        root.right = new MyTreeNode(20);
        root.left.left = new MyTreeNode(8);
        root.left.right = new MyTreeNode(12);
        root.right.left = new MyTreeNode(16);
        root.right.right = new MyTreeNode(25);

        int key = 16;
        boolean isPresentR = isNodePresentR(root, key);
        boolean isPresentI = isNodePresentI(root, key);

        System.out.println(isPresentR + " " + isPresentI);
    }

    private static boolean isNodePresentR(MyTreeNode root, int key) {
        if (root == null)
            return false;

        if (root.val == key)
            return true;

        boolean l = isNodePresentR(root.left, key);
        if (l)
            return true;

        boolean r = isNodePresentR(root.right, key);
        if (r)
            return true;

        return false;
    }

    private static boolean isNodePresentI(MyTreeNode root, int key) {
        if (root == null)
            return false;

        Queue<MyTreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            MyTreeNode t = q.poll();

            if (t.val == key)
                return true;

            if (t.left != null)
                q.offer(t.left);
            if (t.right != null)
                q.offer(t.right);
        }
        return false;
    }
}
