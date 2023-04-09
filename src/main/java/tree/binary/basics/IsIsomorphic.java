package tree.binary.basics;

import tree.binary.MyTreeNode;

class IsIsomorphic {
    /*-
    2 Trees are isomorphic if either one holds true:-
        1. Are the same tree.
        2. Both are mirror of each other
     */
    public static void main(String[] args) {
        /*-
            tree 1                  tree 2
               1                        1
             /   \                    /    \
            2     3                  3      2
           / \   /                    \    / \
          4   5  6                     6  4   5
             / \                             / \
            7   8                           8   7
         */

        MyTreeNode root1 = new MyTreeNode(1);
        root1.left = new MyTreeNode(2);
        root1.right = new MyTreeNode(3);

        root1.left.left = new MyTreeNode(4);
        root1.left.right = new MyTreeNode(5);
        root1.right.left = new MyTreeNode(6);

        root1.left.right.left = new MyTreeNode(7);
        root1.left.right.right = new MyTreeNode(8);

        MyTreeNode root2 = new MyTreeNode(1);
        root2.left = new MyTreeNode(3);
        root2.right = new MyTreeNode(2);

        root2.left.right = new MyTreeNode(6);
        root2.right.left = new MyTreeNode(4);
        root2.right.right = new MyTreeNode(5);

        root2.right.right.left = new MyTreeNode(8);
        root2.right.right.right = new MyTreeNode(7);

        System.out.println("Are trees isomorphic? : " + isIsomorphic(root1, root2));
    }

    private static boolean isIsomorphic(MyTreeNode root1, MyTreeNode root2) {
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        boolean isDataSame = root1.val == root2.val;

        return isDataSame &&
                ((isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right)) || // is the same tree?
                        (isIsomorphic(root1.left, root2.right) && isIsomorphic(root1.right, root2.left))); //is the mirror tree?
    }
}
