package tree.binary.basics;

import tree.binary.MyTreeNode;

class PathNodeToRoot {
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

        StringBuffer path = new StringBuffer();

        int key = 90;
        nodeToRootPath(root, key, path);

        System.out.println(path.toString());
    }

    private static boolean nodeToRootPath(MyTreeNode root, int key, StringBuffer path) {
        if (root == null)
            return false;

        if (root.val == key) {
            path.append(root.val + "<-");
            return true;
        }

        boolean l = nodeToRootPath(root.left, key, path);
        if (l) {
            path.append(root.val + "<-");
            return true;
        }

        boolean r = nodeToRootPath(root.right, key, path);
        if (r) {
            path.append(root.val + "<-");
            return true;
        }

        return false;
    }
}
