package tree.binary.traversal;

import tree.binary.BinaryTreeHelper;
import tree.binary.MyTreeNode;

import java.io.PrintWriter;
import java.util.*;

class VerticalOrderTraversal {
    /*-
             3
           /   \
         9      20
               /  \
             15    7

     Output: [[9],[3,15],[20],[7]]
     */
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode(3);

        root.left = new MyTreeNode(9);
        root.right = new MyTreeNode(20);

        root.right.left = new MyTreeNode(15);
        root.right.right = new MyTreeNode(17);

        VerticalOrderTraversal problem = new VerticalOrderTraversal();

        String res = "Vertical order traversal of the Binary Tree : " + "\n" +
                BinaryTreeHelper.preOrderR(root) + "\n" +
                "Iterative (level order) : " + problem.solveI(root);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private List<List<Integer>> solveI(MyTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, Map<Integer, PriorityQueue<MyTreeNode>>> orderedNodes = preProcessUsingLevelOrder(root);

        for (Map<Integer, PriorityQueue<MyTreeNode>> nodesOrderedByVertical : orderedNodes.values()) {
            // add a List for the current vertical
            result.add(new ArrayList<>());

            List<Integer> levelList = new ArrayList<>();
            for (PriorityQueue<MyTreeNode> nodesOrderedByVerticalAndLevel : nodesOrderedByVertical.values()) {
                while (!nodesOrderedByVerticalAndLevel.isEmpty()) {
                    levelList.add(nodesOrderedByVerticalAndLevel.poll().val);
                }
            }
            result.get(result.size() - 1).addAll(levelList);
        }

        return result;
    }

    private Map<Integer, Map<Integer, PriorityQueue<MyTreeNode>>> preProcessUsingLevelOrder(MyTreeNode root) {
        Map<Integer, Map<Integer, PriorityQueue<MyTreeNode>>> orderedNodes = new TreeMap<>();
        Queue<NodeTuple> q = new LinkedList<>();

        q.offer(new NodeTuple(root, 0, 0));

        while (!q.isEmpty()) {
            NodeTuple currentTuple = q.poll();
            MyTreeNode currentNode = currentTuple.node;
            int vertical = currentTuple.vertical;
            int level = currentTuple.level;

            if (!orderedNodes.containsKey(vertical)) {
                orderedNodes.put(vertical, new TreeMap<>());
            }

            if (!orderedNodes.get(vertical).containsKey(level))
                //orderedNodes.get(vertical).put(level, new PriorityQueue<>((n1, n2) -> n1.val - n2.val));
                orderedNodes.get(vertical).put(level, new PriorityQueue<>(Comparator.comparingInt(n -> n.val)));


            orderedNodes.get(vertical).get(level).offer(currentNode);

            if (currentNode.left != null) q.offer(new NodeTuple(currentNode.left, vertical - 1, level + 1));
            if (currentNode.right != null) q.offer(new NodeTuple(currentNode.right, vertical + 1, level + 1));
        }

        return orderedNodes;
    }

    private record NodeTuple(MyTreeNode node, int vertical, int level) {
    }
}
