package tree.binary.misc;

import tree.binary.MyTreeNode;

class TreeTilt {
    public static void main(String[] args) {
        /*-
          Sample tree
                        4
                      /    \
                     2      9
                   /  \       \
                  3    5       7
         */
        MyTreeNode root = new MyTreeNode(4);
        root.left = new MyTreeNode(2);
        root.right = new MyTreeNode(9);

        root.left.left = new MyTreeNode(3);
        root.left.right = new MyTreeNode(5);

        root.right.right = new MyTreeNode(7);

        getTreeTilt(root);
        System.out.println("Tree Tilt : " + treeTilt);
    }

    static int treeTilt;

    private static int getTreeTilt(MyTreeNode root) {
        if (root == null) return 0;

        int l = getTreeTilt(root.left);
        int r = getTreeTilt(root.right);

        treeTilt += Math.abs(l - r);

        return root.val + l + r;
    }
}
