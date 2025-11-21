package tree.binary.basics;

import tree.binary.MyTreeNode;

class PrintKLevelsFromRoot {
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(68);
        root.left = new MyTreeNode(36);
        root.right = new MyTreeNode(71);

        root.left.left = new MyTreeNode(29);
        root.left.left.left = new MyTreeNode(27);
        root.left.left.right = new MyTreeNode(32);

        root.right.left = new MyTreeNode(70);
        root.right.right = new MyTreeNode(77);

        int k = 2;
        printNodesAtLevel(root, k);
    }

    private static void printNodesAtLevel(MyTreeNode root, int l) {
        if (root == null)
            return;

        if (l == 0) {
            System.out.print(root.val + "->");
            return;
        }

        printNodesAtLevel(root.left, l - 1);
        printNodesAtLevel(root.right, l - 1);
    }
}