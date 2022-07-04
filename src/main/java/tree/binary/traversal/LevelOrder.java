package tree.binary.traversal;

import tree.binary.MyTreeNode;

import java.util.LinkedList;
import java.util.Queue;

class LevelOrder {
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(68);
        root.left = new MyTreeNode(36);
        root.right = new MyTreeNode(71);

        root.left.left = new MyTreeNode(29);
        root.left.left.left = new MyTreeNode(27);
        root.left.left.right = new MyTreeNode(32);

        root.right.left = new MyTreeNode(70);
        root.right.right = new MyTreeNode(77);

        levelOrder(root);
    }

    private static void levelOrder(MyTreeNode root) {
        if (root == null)
            return;

        Queue<MyTreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            MyTreeNode t = q.poll();

            System.out.print(t.val + " ");

            if (t.left != null)
                q.offer(t.left);
            if (t.right != null)
                q.offer(t.right);
        }
    }
}
