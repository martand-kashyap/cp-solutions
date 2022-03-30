package tree.binary.basics;

import tree.binary.MyTreeNode;

class SumOfNodes {
    public static void main(String[] args) {
        /*
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

        int r = sumOfNodes(root);
        System.out.println(r);
    }

    private static int sumOfNodes(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = sumOfNodes(root.left);
        int r = sumOfNodes(root.right);

        return root.val + l + r;
    }
}
