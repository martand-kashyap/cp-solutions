package tree.binary.basics;

import tree.binary.MyTreeNode;

import java.util.LinkedList;

class PathRootToNodeAll {
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

        LinkedList<Integer> path = new LinkedList<>();
        allRootToNodePathsR(root, path);
    }

    private static void allRootToNodePathsR(MyTreeNode root, LinkedList<Integer> path) {
        if (root == null)
            return;

        path.addLast(root.val);

        if (root.left == null && root.right == null) {
            System.out.println(path);
            path.removeLast();
            return;
        }

        allRootToNodePathsR(root.left, path);
        allRootToNodePathsR(root.right, path);

        path.removeLast();
    }
}
