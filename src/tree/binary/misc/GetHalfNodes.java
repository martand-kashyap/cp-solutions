package tree.binary.misc;

import tree.binary.MyTreeNode;

import java.util.LinkedList;

class GetHalfNodes {
    public static void main(String[] args) {
        /*
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

        LinkedList<Integer> halfNodes = new LinkedList<>();
        getHalfNodes(root, halfNodes);

        System.out.println(halfNodes);
    }

    private static void getHalfNodes(MyTreeNode root, LinkedList<Integer> halfNodes) {
        if (root == null)
            return;

        if ((root.left == null && root.right != null) ||
            (root.left != null && root.right == null))
            halfNodes.addLast(root.val);

        getHalfNodes(root.left, halfNodes);
        getHalfNodes(root.right, halfNodes);
    }
}
