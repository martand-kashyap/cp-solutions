package tree.binary.basics;

import tree.binary.MyTreeNode;

class LCA {
    /*-
              1
            /   \
           2      3
          / \    / \
         4   5  6   7
     */
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(1);

        root.left = new MyTreeNode(2);
        root.right = new MyTreeNode(3);

        root.left.left = new MyTreeNode(4);
        root.left.right = new MyTreeNode(5);
        root.right.left = new MyTreeNode(6);
        root.right.right = new MyTreeNode(7);

        MyTreeNode node1 = new MyTreeNode(5);
        MyTreeNode node2 = new MyTreeNode(2);
        MyTreeNode result =
                optimized(root,
                        node1,
                        node2
                );
        System.out.println("The LCA for " + node1.val + " & " + node2.val
                + "\nis : " + result.val);
    }

    private static MyTreeNode optimized(MyTreeNode root, MyTreeNode p, MyTreeNode q) {
        //if the current node is either null or 1 of the nodes we are looking for
        if (root == null || root.val == p.val || root.val == q.val) return root;

        //search in LST
        MyTreeNode l = optimized(root.left, p, q);

        //search in RST
        MyTreeNode r = optimized(root.right, p, q);

        if (l != null && r != null)
            return root;
        else
            return l != null ? l : r;
    }
}
