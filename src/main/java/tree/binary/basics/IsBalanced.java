package tree.binary.basics;

import tree.binary.MyTreeNode;

class HeightBalancePair {
    int height;
    boolean isBalanced;

    public HeightBalancePair() {
        height = 0;
        isBalanced = true;
    }
}

class IsBalanced {
    public static void main(String[] args) {
		/*-
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
        root.right.left.right.left = new MyTreeNode(90);

        System.out.println("is tree balanced : " + isTreeBalancedR(root));
        System.out.println("is tree balanced (pair approach) : " + isTreeBalancedNodePairR(root).isBalanced);
        isTreeBalanceTravelChain(root);
        System.out.println("is tree balanced (travel & chain approach) : " + isTreeBalanced);
    }

    static boolean isTreeBalanced = true;

    private static int isTreeBalanceTravelChain(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = isTreeBalanceTravelChain(root.left);
        int r = isTreeBalanceTravelChain(root.right);

        if (Math.abs(l - r) > 1)
            isTreeBalanced = false;

        return Math.max(l, r) + 1;
    }

    private static HeightBalancePair isTreeBalancedNodePairR(MyTreeNode root) {
        if (root == null)
            return new HeightBalancePair();

        HeightBalancePair l = isTreeBalancedNodePairR(root.left);
        HeightBalancePair r = isTreeBalancedNodePairR(root.right);

        HeightBalancePair c = new HeightBalancePair();
        c.height = Math.max(l.height, r.height) + 1;
        c.isBalanced = l.isBalanced && r.isBalanced && Math.abs(l.height - r.height) <= 1;

        return c;
    }

    private static boolean isTreeBalancedR(MyTreeNode root) {
        if (root == null)
            return true;

        int l = height(root.left);
        int r = height(root.right);

        return isTreeBalancedR(root.left) &&
                isTreeBalancedR(root.right) &&
                Math.abs(l - r) <= 1;
    }

    private static int height(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = height(root.left);
        int r = height(root.right);

        return 1 + Math.max(l, r);
    }
}
