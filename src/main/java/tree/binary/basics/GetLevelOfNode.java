package tree.binary.basics;

import java.util.LinkedList;
import java.util.Queue;

import tree.binary.MyTreeNode;

class GetLevelOfNode {

    public static void main(String[] args) {
        /**
         * Sample tree
         *               15
         *             /    \
         *           10      20
         *          /  \    /  \
         *         8   12  16   25
         */
        MyTreeNode root = new MyTreeNode(15);
        root.left = new MyTreeNode(10);
        root.right = new MyTreeNode(20);
        root.left.left = new MyTreeNode(8);
        root.left.right = new MyTreeNode(12);
        root.right.left = new MyTreeNode(16);
        root.right.right = new MyTreeNode(25);

        System.out.println(getLevelOfNodeR(root, 16, 0));
        getLevelOfNodeI(root, 16);
    }

    private static void getLevelOfNodeI(MyTreeNode root, int key) {
        Queue<MyTreeNode> q = new LinkedList<>();
        q.offer(root);
        int cLevel = 0;

        while (!q.isEmpty()) {
            int lSize = q.size();

            for (int i = 0; i < lSize; i++) {
                MyTreeNode curr = q.poll();

                if (curr.val == key) {
                    System.out.println(cLevel);
                    break;
                }

                if (curr.left != null)
                    q.offer(curr.left);

                if (curr.right != null)
                    q.offer(curr.right);
            }

            cLevel += 1;
        }
    }

    private static int getLevelOfNodeR(MyTreeNode root, int key, int level) {
        if (root == null)
            return -1;

        if (root.val == key)
            return level;

        int l = getLevelOfNodeR(root.left, key, level + 1);
        if (l > -1)
            return l;

        int r = getLevelOfNodeR(root.right, key, level + 1);
        return r;
    }
}
