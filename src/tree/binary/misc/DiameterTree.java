package tree.binary.misc;

import tree.binary.MyTreeNode;

class HeightDiameterPair {
    int height;
    int diameter;

    public HeightDiameterPair() {
        this.height = 0;
        this.diameter = 0;
    }
}

class DiameterTree {
    static int optDiameter;
    
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

        System.out.println("diameter : " + getDiameter(root));

        getHeightModifiedR(root);
        System.out.println("diameter (optimized) ver 1: " + optDiameter);

        System.out.println("diameter (optimized) ver 2: " + getDiameterModifiedR(root).diameter);
    }


    private static int getDiameter(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = getDiameter(root.left);
        int r = getDiameter(root.right);

        int lH = heightR(root.left);
        int rH = heightR(root.right);

        int c = lH + rH + 1;

        return Math.max(c, Math.max(l, r));
    }

    private static int heightR(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = heightR(root.left);
        int r = heightR(root.right);

        return Math.max(l, r) + 1;
    }

    private static HeightDiameterPair getDiameterModifiedR(MyTreeNode root) {
        if (root == null) {
            return new HeightDiameterPair();
        }

        HeightDiameterPair l = getDiameterModifiedR(root.left);
        HeightDiameterPair r = getDiameterModifiedR(root.right);

        HeightDiameterPair c = new HeightDiameterPair();
        c.height = Math.max(l.height, r.height) + 1;
        int currDiameter = l.height + r.height + 1;
        c.diameter = Math.max(currDiameter, Math.max(l.diameter, r.diameter));

        return c;
    }

    private static int getHeightModifiedR(MyTreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 1;

        int l = getHeightModifiedR(root.left);
        int r = getHeightModifiedR(root.right);

        optDiameter = Math.max(optDiameter, l + r + 1);

        return Math.max(l, r) + 1;
    }
}
