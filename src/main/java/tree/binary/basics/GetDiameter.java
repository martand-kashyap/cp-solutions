package tree.binary.basics;

import tree.binary.MyTreeNode;

class GetDiameter {
    /*-
    The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two end nodes.

        Input:
               1
             /  \
            2    3
        Output: 3

        Input:
                 10
                /   \
              20    30
            /   \
           40   60
        Output: 4
     */
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(10);

        root.left = new MyTreeNode(20);
        root.right = new MyTreeNode(30);

        root.left.left = new MyTreeNode(40);
        root.left.right = new MyTreeNode(60);

        modifiedHeight(root);
        System.out.println(diameterOfTree);
    }

    private int bruteforce(MyTreeNode root) {
        if (root == null) return 0;

        int lH = height(root.left);
        int rH = height(root.right);

        int d = lH + rH + 2;

        int l = bruteforce(root.left);
        int r = bruteforce(root.right);

        return Math.max(d, Math.max(l, r));
    }

    private int height(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = height(root.left);
        int r = height(root.right);

        return 1 + Math.max(l, r);
    }

    private static int diameterOfTree = 0;

    private static int modifiedHeight(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = modifiedHeight(root.left);
        int r = modifiedHeight(root.right);

        diameterOfTree = Math.max(diameterOfTree, l + r + 1);

        return 1 + Math.max(l, r);
    }
}
