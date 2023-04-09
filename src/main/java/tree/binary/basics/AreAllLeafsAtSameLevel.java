package tree.binary.basics;

import tree.binary.BinaryTreeHelper;
import tree.binary.MyTreeNode;

import java.util.LinkedList;
import java.util.Queue;

class AreAllLeafsAtSameLevel {
    /*-
    Given a Binary Tree, check if all leaves are at same level or not.

    Example 1:
                10
              /    \
            20      30
           /  \
         10    15
     o/p: false
     Example 2:
                1
              /   \
             2     3
     o/p: true
     */
    public static void main(String[] args) {
        /*MyTreeNode root = new MyTreeNode(1);
        root.left = new MyTreeNode(2);
        root.right = new MyTreeNode(3);*/

        MyTreeNode root = new MyTreeNode(10);

        root.left = new MyTreeNode(20);
        root.right = new MyTreeNode(30);

        root.left.left = new MyTreeNode(10);
        root.left.right = new MyTreeNode(15);

        System.out.println("Are all leafs on the same level in the tree : \n"
                + BinaryTreeHelper.preOrderR(root) + "\n"
                + recursive(root));
    }

    private static boolean levelOrderI(MyTreeNode root) {
        if (root == null) return true;

        Queue<MyTreeNode> q = new LinkedList<>();
        q.offer(root);

        int firstLeafLevel = -1;
        int cLvl = 1;
        while (!q.isEmpty()) {
            int lvlSize = q.size();

            for (int i = 0; i < lvlSize; i++) {
                MyTreeNode c = q.poll();

                if (isLeaf(c)) {
                    if (firstLeafLevel == -1) firstLeafLevel = cLvl;
                    if (firstLeafLevel != cLvl) return false;
                }

                if (c.left != null) q.offer(c.left);
                if (c.right != null) q.offer(c.right);
            }

            cLvl += 1;
        }

        return true;
    }

    private static boolean isLeaf(MyTreeNode n) {
        return (n != null && n.left == null && n.right == null);
    }

    private static int firstLeafLevel = -1;

    private static boolean recursive(MyTreeNode root) {
        return helper(root, 1);
    }

    private static boolean helper(MyTreeNode root, int lvl) {
        if (root == null) return true;

        if (isLeaf(root)) {
            if (firstLeafLevel == -1) firstLeafLevel = lvl;
            return (firstLeafLevel == lvl);
        }

        boolean l = helper(root.left, lvl + 1);
        boolean r = helper(root.right, lvl + 1);

        return l && r;
    }
}
