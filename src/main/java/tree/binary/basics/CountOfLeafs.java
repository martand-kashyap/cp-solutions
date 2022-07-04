package tree.binary.basics;

import tree.binary.MyTreeNode;

class CountOfLeafs {
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(68);
        root.left = new MyTreeNode(36);
        root.right = new MyTreeNode(71);

        root.left.left = new MyTreeNode(29);

        root.left.left.left = new MyTreeNode(27);
        root.left.left.right = new MyTreeNode(32);

        root.right.left = new MyTreeNode(70);
        root.right.right = new MyTreeNode(77);

        System.out.println(countLeafs(root));
    }

    private static int countLeafs(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = countLeafs(root.left);
        int r = countLeafs(root.right);

        return (root.left == null && root.right == null) ? 1 + l + r : l + r;
    }
}
