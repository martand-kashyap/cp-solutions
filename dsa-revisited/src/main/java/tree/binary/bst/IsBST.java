package tree.binary.bst;

import tree.binary.MyTreeNode;

class IsBST {
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(2);

        root.left = new MyTreeNode(1);
        root.right = new MyTreeNode(3);

        System.out.println(isBSTR(root));
    }

    private static boolean isBSTR(MyTreeNode root) {
        return helper(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private static boolean helper(MyTreeNode root, int minValue, int maxValue) {
        if (root == null) return true;
        if (minValue >= root.val || root.val >= maxValue) return false;

        boolean l = helper(root.left, minValue, root.val);
        boolean r = helper(root.right, root.val, maxValue);

        return (l && r);
    }
}
