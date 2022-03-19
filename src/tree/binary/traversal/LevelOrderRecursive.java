package tree.binary.traversal;

import tree.binary.MyTreeNode;

public class LevelOrderRecursive {
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(68);
        root.left = new MyTreeNode(36);
        root.right = new MyTreeNode(71);

        root.left.left = new MyTreeNode(29);
        
        root.left.left.left = new MyTreeNode(27);
        root.left.left.right = new MyTreeNode(32);

        root.right.left = new MyTreeNode(70);
        root.right.right = new MyTreeNode(77);

        levelOrderRecursive(root);
    }

    private static void levelOrderRecursive(MyTreeNode root) {
        if (root == null)
            return;

        int h = height(root);

        for (int i = 0; i < h; i++)
            printNodesAtLevel(root, i);
    }

    private static void printNodesAtLevel(MyTreeNode root, int i) {
        if (root == null || i < 0)
            return;

        if (i == 0) {
            System.out.print(root.val + " ");
            return;
        }

        printNodesAtLevel(root.left, i - 1);
        printNodesAtLevel(root.right, i - 1);
    }

    private static int height(MyTreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }
}