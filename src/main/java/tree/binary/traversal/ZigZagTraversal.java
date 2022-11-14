package tree.binary.traversal;

import tree.binary.MyTreeNode;

import java.io.PrintWriter;
import java.util.*;

class ZigZagTraversal {
    public static void main(String[] args) {
        /*-
        Sample tree

                   15
                 /   \
               10     20
              /  \   /  \
             8   12 16  25
        */
        MyTreeNode root = new MyTreeNode(15);
        root.left = new MyTreeNode(10);
        root.right = new MyTreeNode(20);
        root.left.left = new MyTreeNode(8);
        root.left.right = new MyTreeNode(12);
        root.right.left = new MyTreeNode(16);
        root.right.right = new MyTreeNode(25);

        ZigZagTraversal problem = new ZigZagTraversal();
        String res = "Recursive (DFS) T(n) = O(n) : " + problem.spiralR(root) + "\n" +
                "Iterative (BFS) T(n) = O(n) : " + problem.spiralI(root);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String spiralR(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();

        int h = height(root);
        boolean rightToLeft = false;

        for (int i = 0; i < h; i++) {
            printNodesAtLevel(root, i, rightToLeft, result);
            rightToLeft = !rightToLeft;
        }

        return result.toString();
    }

    private void printNodesAtLevel(MyTreeNode root, int i, boolean rightToLeft, List<Integer> result) {
        if (root == null)
            return;

        if (i == 0) {
            result.add(root.val);
            return;
        }

        if (rightToLeft) {
            printNodesAtLevel(root.right, i - 1, rightToLeft, result);
            printNodesAtLevel(root.left, i - 1, rightToLeft, result);
        } else {
            printNodesAtLevel(root.left, i - 1, rightToLeft, result);
            printNodesAtLevel(root.right, i - 1, rightToLeft, result);
        }
    }

    private int height(MyTreeNode root) {
        if (root == null)
            return 0;

        int l = height(root.left);
        int r = height(root.right);

        return 1 + Math.max(l, r);
    }

    private String spiralI(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result.toString();

        boolean rightToLeft = false;
        Queue<MyTreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> level = new ArrayList<>();

            for (int l = 0; l < levelSize; l += 1) {
                MyTreeNode t = q.poll();

                level.add(t.val);

                if (t.left != null)
                    q.offer(t.left);
                if (t.right != null)
                    q.offer(t.right);
            }
            if (rightToLeft)
                Collections.reverse(level);

            rightToLeft = !rightToLeft;

            result.addAll(level);
        }

        return result.toString();
    }
}
