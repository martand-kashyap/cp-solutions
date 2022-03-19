package tree.binary.basics;

import tree.binary.MyTreeNode;

class CountOfNonLeafs {
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(68);
        root.left = new MyTreeNode(36);
        root.right = new MyTreeNode(71);

        root.left.left = new MyTreeNode(29);

        root.left.left.left = new MyTreeNode(27);
        root.left.left.right = new MyTreeNode(32);

        root.right.left = new MyTreeNode(70);
        root.right.right = new MyTreeNode(77);

        System.out.println(countNonLeafs(root));
    }

    private static int countNonLeafs(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = countNonLeafs(root.left);
        int r = countNonLeafs(root.right);

        return (root.left == null && root.right == null) ?  l + r : 1+l + r;
    }
}
