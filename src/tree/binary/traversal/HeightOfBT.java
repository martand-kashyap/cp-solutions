package tree.binary.traversal;

class HeightOfBT {
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(4);
        root.left = new MyTreeNode(5);
        root.right = new MyTreeNode(10);

        root.left.left = new MyTreeNode(15);
        root.left.right = new MyTreeNode(20);

        System.out.println(height(root));
    }

    private static int height(MyTreeNode root) {
        if (root == null)
            return -1;

        int l = height(root.left);
        int r = height(root.right);

        return 1 + Math.max(l, r);
    }
}
