package tree.binary.traversal;

import tree.binary.MyTreeNode;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class LevelOrderTraversal {
    /*-
                  68
               /      \
             36         71
            /         /    \
          29        70      77
         /  \
        27   32

     */
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(68);

        root.left = new MyTreeNode(36);
        root.right = new MyTreeNode(71);

        root.left.left = new MyTreeNode(29);
        root.left.left.left = new MyTreeNode(27);
        root.left.left.right = new MyTreeNode(32);

        root.right.left = new MyTreeNode(70);
        root.right.right = new MyTreeNode(77);

        LevelOrderTraversal problem = new LevelOrderTraversal();
        String res = "Recursive (DFS) T(n) = O(n) : " + problem.levelOrderR(root) + "\n" +
                "Iterative (BFS) T(n) = O(n) : " + problem.levelOrderI(root);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String levelOrderR(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result.toString();

        int h = height(root);
        for (int i = 0; i < h; i++)
            printNodesAtLevel(root, i, result);

        return result.toString();
    }

    private void printNodesAtLevel(MyTreeNode root, int i, List<Integer> result) {
        if (root == null || i < 0)
            return;

        if (i == 0) {
            result.add(root.val);
            return;
        }

        printNodesAtLevel(root.left, i - 1, result);
        printNodesAtLevel(root.right, i - 1, result);
    }

    private int height(MyTreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }

    private String levelOrderI(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result.toString();

        Queue<MyTreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            MyTreeNode t = q.poll();

            result.add(t.val);

            if (t.left != null)
                q.offer(t.left);
            if (t.right != null)
                q.offer(t.right);
        }

        return result.toString();
    }
}
