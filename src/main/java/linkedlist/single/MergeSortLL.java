package linkedlist.single;

import java.io.PrintWriter;

class MergeSortLL {
    public static void main(String[] args) {
        int[] nodeValues = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
        SingleLinkedListNode head = new SingleLinkedListNode(nodeValues[0], null);
        for (int i = 1; i < nodeValues.length; i++) {
            LinkedListHelper.insertLast(nodeValues[i], head);
        }

        MergeSortLL problem = new MergeSortLL();
        String res = "Before : \n" +
                LinkedListHelper.displayList(head) + "\nAfter \n" +
                LinkedListHelper.displayList(problem.mergeSortLL(head));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private SingleLinkedListNode mergeSortLL(SingleLinkedListNode head) {
        SingleLinkedListNode middleNode = LinkedListHelper.findMidNodeUsingFloydCycleFinding(head);
        SingleLinkedListNode a = mergeSortLL(head);
        SingleLinkedListNode b = mergeSortLL(middleNode);

        return merge(a, b);
    }

    private SingleLinkedListNode merge(SingleLinkedListNode a, SingleLinkedListNode b) {
        if (a == null) return b;
        if (b == null) return a;

        return null;
    }

}
