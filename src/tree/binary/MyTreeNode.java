package tree.binary;

public class MyTreeNode {
    int val;
    MyTreeNode left, right;

    public MyTreeNode() {
        this.val = -1;
        this.left = null;
        this.right = null;
    }

    public MyTreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public MyTreeNode(int val, MyTreeNode left, MyTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}