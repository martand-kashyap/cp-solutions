package tree.binary.misc;

import tree.binary.MyTreeNode;

import java.util.LinkedList;

class LeftCloneNodes {
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

        LinkedList<Integer> pre = new LinkedList<>();
        preOrder(root, pre);
        System.out.println("preorder before : " + pre);

        MyTreeNode newRoot = leftCloneNodes(root);

        pre.clear();
        preOrder(newRoot, pre);
        System.out.println("preorder after : " + pre);
    }

    private static MyTreeNode leftCloneNodes(MyTreeNode root) {
        if (root == null)
            return null;

        MyTreeNode l = leftCloneNodes(root.left);
        MyTreeNode r = leftCloneNodes(root.right);

        MyTreeNode clone = new MyTreeNode(root.val);

        clone.left = root.left;

        root.left = clone;
        root.right = r;

        return root;
    }

    private static void preOrder(MyTreeNode root, LinkedList<Integer> pre) {
        if (root == null)
            return;

        pre.addLast(root.val);
        preOrder(root.left, pre);
        preOrder(root.right, pre);
    }
}
