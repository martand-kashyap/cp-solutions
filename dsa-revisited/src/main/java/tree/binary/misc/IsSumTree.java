package tree.binary.misc;

import tree.binary.MyTreeNode;

class SumPair {
    int subTreeSum;
    boolean isSumNode;

    public SumPair() {
    }

    public SumPair(int sum, boolean isSumNode) {
        this.subTreeSum = sum;
        this.isSumNode = isSumNode;
    }
}

class IsSumTree {
    public static void main(String[] args) {
        /*-
          Sample tree
                        26
                      /    \
                    10      3
                   /  \       \
                  4    6       3
         */
        MyTreeNode root = new MyTreeNode(26);
        root.left = new MyTreeNode(10);
        root.right = new MyTreeNode(3);

        root.left.left = new MyTreeNode(4);
        root.left.right = new MyTreeNode(6);

        root.right.right = new MyTreeNode(3);

        System.out.println("Is Sum Tree in O(n^2) : " + isSumTreeR(root));
        System.out.println("Is Sum Tree in O(n) [optimized]: " + isSumTreeROptimized(root).isSumNode);
    }

    private static SumPair isSumTreeROptimized(MyTreeNode root) {
        if (root == null)
            return new SumPair(0, true);

        if (isLeaf(root))
            return new SumPair(root.val, true);

        SumPair l = isSumTreeROptimized(root.left);
        SumPair r = isSumTreeROptimized(root.right);

        SumPair curr = new SumPair();
        curr.subTreeSum = l.subTreeSum + r.subTreeSum + root.val;
        curr.isSumNode = root.val == l.subTreeSum + r.subTreeSum && l.isSumNode && r.isSumNode;

        return curr;
    }

    private static boolean isLeaf(MyTreeNode root) {
        return root != null && root.left == null && root.right == null;
    }

    private static boolean isSumTreeR(MyTreeNode root) {
        if (root == null || isLeaf(root))
            return true;

        int sumLeft = getSumOfNodes(root.left);
        int sumRight = getSumOfNodes(root.right);

        boolean currNode = root.val == sumLeft + sumRight;
        boolean l = isSumTreeR(root.left);
        boolean r = isSumTreeR(root.right);

        return currNode && l && r;
    }

    private static int getSumOfNodes(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = getSumOfNodes(root.left);
        int r = getSumOfNodes(root.right);

        return l + r + root.val;
    }
}
