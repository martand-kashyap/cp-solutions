package tree.binary.traversal;

import tree.binary.BinaryTreeHelper;
import tree.binary.MyTreeNode;

import java.io.PrintWriter;
import java.util.*;

class TopView {
    /*-
                 3
               /   \
             9      20
                   /  \
                 15    7

         Output: [9, 3, 20, 7]
     */
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(3);

        root.left = new MyTreeNode(9);
        root.right = new MyTreeNode(20);

        root.right.left = new MyTreeNode(15);
        root.right.right = new MyTreeNode(17);

        TopView problem = new TopView();

        String res = "Top view of the Binary Tree : " + "\n" +
                BinaryTreeHelper.preOrderR(root) + "\n" +
                "Iterative (level order) : " + problem.solveI(root);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private List<Integer> solveI(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, MyTreeNode> verticalNodeMap = levelOrderTraversal(root);

        for (MyTreeNode node : verticalNodeMap.values()) {
            result.add(node.val);
        }

        return result;
    }

    private Map<Integer, MyTreeNode> levelOrderTraversal(MyTreeNode root) {
        Map<Integer, MyTreeNode> verticalNodeMap = new TreeMap<>();
        Queue<NodeVerticalPair> q = new LinkedList<>();
        q.offer(new NodeVerticalPair(root, 0));

        while (!q.isEmpty()) {
            NodeVerticalPair currentPair = q.poll();
            MyTreeNode currentNode = currentPair.node;
            int currentVertical = currentPair.vertical;

            //insert in map, only if seeing it for the first time
            if (!verticalNodeMap.containsKey(currentVertical) || verticalNodeMap.get(currentVertical) == null) {
                verticalNodeMap.put(currentVertical, currentNode);
            }

            if (currentNode.left != null)
                q.offer(new NodeVerticalPair(currentNode.left, currentVertical - 1));
            if (currentNode.right != null)
                q.offer(new NodeVerticalPair(currentNode.right, currentVertical + 1));
        }

        return verticalNodeMap;
    }

    private record NodeVerticalPair(MyTreeNode node, int vertical) {
    }
}
