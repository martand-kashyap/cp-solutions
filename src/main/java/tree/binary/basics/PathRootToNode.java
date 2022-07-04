package tree.binary.basics;

import tree.binary.MyTreeNode;

import java.util.ArrayList;

class PathRootToNode {
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

        ArrayList<Integer> path = new ArrayList<>();
        int key = 90;
        rootToNodePath(root, key, path);

        System.out.println(path.toString());
    }

    private static boolean rootToNodePath(MyTreeNode root, int key, ArrayList<Integer> path) {
        if (root == null)
            return false;

        path.add(root.val);

        boolean l = rootToNodePath(root.left, key, path);
        boolean r = rootToNodePath(root.right, key, path);
        if (root.val == key || l || r)
            return true;

        path.remove(path.size() - 1);
        return false;
    }
}
