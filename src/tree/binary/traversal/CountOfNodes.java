package tree.binary.traversal;

class CountOfNodes {
	public static void main(String[] args) {
		MyTreeNode root = new MyTreeNode(4);
		root.left = new MyTreeNode(5);
		root.right = new MyTreeNode(10);

		root.left.left = new MyTreeNode(15);
		root.left.right = new MyTreeNode(20);

		System.out.println(countNodes(root));
	}

	private static int countNodes(MyTreeNode root) {
		if (root == null)
			return 0;

		int l = countNodes(root.left);
		int r = countNodes(root.right);

		return 1 + l + r;
	}

}
