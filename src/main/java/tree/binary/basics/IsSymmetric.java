package tree.binary.basics;

import tree.binary.BinaryTreeHelper;
import tree.binary.MyTreeNode;

class IsSymmetric {
    /*-
    Given a Binary Tree, check whether it is Symmetric or not, i.e. whether the binary tree is a Mirror image of itself or not.
    Example 1:
             5
           / | \
          1  |  1
         /   |   \
        2    |    2
    o/p: true
     */
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(5);

        root.left = new MyTreeNode(1);
        root.right = new MyTreeNode(1);

        root.left.left = new MyTreeNode(2);
        root.right.right = new MyTreeNode(2);

        System.out.println("Is the Tree symmetric : \n"
                + BinaryTreeHelper.preOrderR(root) + "\n"
                + isSymmetric(root));
    }

    private static boolean isSymmetric(MyTreeNode root) {
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(MyTreeNode p, MyTreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        boolean l = isMirror(p.left, q.right);
        boolean r = isMirror(p.right, q.left);
        boolean c = p.val == q.val;

        return (l && r && c);
    }
}
