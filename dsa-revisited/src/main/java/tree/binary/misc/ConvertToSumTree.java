package tree.binary.misc;

import tree.binary.MyTreeNode;

public class ConvertToSumTree {
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

        convertToSumTree(root);

        System.out.println(root.val);
    }

    private static void convertToSumTree(MyTreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            root.val=0;
            return ;
        }

        convertToSumTree(root.left);
        convertToSumTree(root.right);

        root.val = (root.left != null ? root.left.val : 0) +
                (root.right != null ? root.right.val : 0);
    }
}
