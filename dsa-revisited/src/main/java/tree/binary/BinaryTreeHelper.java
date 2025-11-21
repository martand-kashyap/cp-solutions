package tree.binary;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeHelper {

    public static List<Integer> preOrderR(MyTreeNode root) {
        List<Integer> preOrder = new ArrayList<>();

        preOrderHelper(root, preOrder);

        return preOrder;
    }

    private static void preOrderHelper(MyTreeNode root, List<Integer> preOrderTraversal) {
        if (root == null) return;

        preOrderTraversal.add(root.val);
        preOrderHelper(root.left, preOrderTraversal);
        preOrderHelper(root.right, preOrderTraversal);
    }

    public static List<Integer> inOrderR(MyTreeNode root) {
        List<Integer> inOrder = new ArrayList<>();

        inOrderHelper(root, inOrder);

        return inOrder;
    }

    private static void inOrderHelper(MyTreeNode root, List<Integer> inOrderTraversal) {
        if (root == null) return;

        inOrderHelper(root.left, inOrderTraversal);
        inOrderTraversal.add(root.val);
        inOrderHelper(root.right, inOrderTraversal);
    }

    public static List<Integer> postOrderR(MyTreeNode root) {
        List<Integer> preOrder = new ArrayList<>();

        postOrderHelper(root, preOrder);

        return preOrder;
    }

    private static void postOrderHelper(MyTreeNode root, List<Integer> postOrderTraversal) {
        if (root == null) return;

        postOrderHelper(root.left, postOrderTraversal);
        postOrderHelper(root.right, postOrderTraversal);
        postOrderTraversal.add(root.val);
    }
}
