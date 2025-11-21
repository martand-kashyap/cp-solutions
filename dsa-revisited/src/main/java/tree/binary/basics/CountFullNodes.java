package tree.binary.basics;

import tree.binary.MyTreeNode;

import java.util.LinkedList;

class CountFullNodes {
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

        System.out.println("recursive : " + countCompleteNodesR(root));
        System.out.println("iterative : " + countCompleteNodesI(root));
    }

    private static int countCompleteNodesI(MyTreeNode root) {
        if (root == null)
            return 0;

        int countFullNodes = 0;
        LinkedList<MyTreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            MyTreeNode c = q.poll();

            if (c.left != null && c.right != null)
                countFullNodes++;

            if (c.left != null)
                q.offer(c.left);
            if (c.right != null)
                q.offer(c.right);
        }
        return countFullNodes;
    }

    private static int countCompleteNodesR(MyTreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 0;

        int l = countCompleteNodesR(root.left);
        int r = countCompleteNodesR(root.right);

        int c = root.left != null && root.right != null ? 1 : 0;

        return l + r + c;
    }
}
