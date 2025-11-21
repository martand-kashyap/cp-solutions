package tree.binary.traversal;

import tree.binary.MyTreeNode;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class RightView {
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

        RightView problem = new RightView();
        String res = /*"Recursive (DFS) T(n) = O(n) : " + problem.levelOrderR(root) + "\n" +*/
                "Iterative (BFS) T(n) = O(n) : " + problem.levelOrderI(root);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String levelOrderI(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result.toString();

        Queue<MyTreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int l = 0; l < levelSize; l++) {
                MyTreeNode c = q.poll();

                if (l == levelSize - 1) result.add(c.val);

                if (c.left != null)
                    q.offer(c.left);
                if (c.right != null)
                    q.offer(c.right);
            }
        }

        return result.toString();
    }
}
