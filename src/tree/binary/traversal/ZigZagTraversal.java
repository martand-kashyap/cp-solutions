package tree.binary.traversal;

import tree.binary.MyTreeNode;

class ZigZagTraversal {
    public static void main(String[] args) {
        /**
         * Sample tree
         *          15
         *        /   \
         *      10     20
         *     /  \   /  \
         *    8   12 16  25
         */
        MyTreeNode root = new MyTreeNode(15);
        root.left = new MyTreeNode(10);
        root.right = new MyTreeNode(20);
        root.left.left = new MyTreeNode(8);
        root.left.right = new MyTreeNode(12);
        root.right.left = new MyTreeNode(16);
        root.right.right = new MyTreeNode(25);

        zigZagTraversal(root);
    }

    private static void zigZagTraversal(MyTreeNode root) {
        int h = height(root);
        boolean isRev = false;

        for (int i = 0; i < h; i++) {
            printNodesAtLevel(root, i, isRev);
            isRev = !isRev;
        }
    }

    private static void printNodesAtLevel(MyTreeNode root, int i, boolean isRev) {
        if (root == null)
            return;

        if (i == 0) {
            System.out.print(root.val + " ");
            return;
        }

        if(!isRev) {
            printNodesAtLevel(root.left, i - 1, isRev);
            printNodesAtLevel(root.right, i - 1, isRev);
        }
        else {
            printNodesAtLevel(root.right, i - 1, isRev);
            printNodesAtLevel(root.left, i - 1, isRev);
        }
    }

    private static int height(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = height(root.left);
        int r = height(root.right);

        return 1 + Math.max(l, r);
    }
}
