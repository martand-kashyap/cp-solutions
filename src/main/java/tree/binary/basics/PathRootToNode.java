package tree.binary.basics;

import tree.binary.MyTreeNode;

import java.util.ArrayList;

class PathRootToNode {
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

        ArrayList<Integer> path = new ArrayList<>();
        int key = 90;
        rootToNodePath(root, key, path);

        System.out.println(path);
    }

    private static boolean rootToNodePath(MyTreeNode root, int target, ArrayList<Integer> path) {
        //if the current node is empty, then cannot compare with target => target is not found
        if (root == null) return false;

        //take the current node into the path
        path.add(root.val);

        //target has been found
        if (root.val == target) return true;

        //search in the LST of current node
        boolean l = rootToNodePath(root.left, target, path);
        //if found in LST, no need to search further
        if (l) return true;

        //search in the RST of current node
        boolean r = rootToNodePath(root.right, target, path);
        //if found in RST, no need to search further
        if (r) return true;

        //when returning from the current node, remove the current node from the path
        path.remove(path.size() - 1);

        //when returning from the current node, target is not found
        return false;
    }
}
