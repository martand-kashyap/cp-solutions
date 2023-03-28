package linkedlist.single;

import java.io.PrintWriter;

class SwapNodesInGroupsOf2 {
    public static void main(String[] args) {
        int[] nodeValues = {10, 20, 30, 40, 50};
        SingleLinkedListNode head = new SingleLinkedListNode(10, null);
        for (int i = 1; i < nodeValues.length; i++) {
            LinkedListHelper.insertLast(nodeValues[i], head);
        }

        SwapNodesInGroupsOf2 problem = new SwapNodesInGroupsOf2();
        String res = "Before : \n" +
                LinkedListHelper.displayList(head) + "\nAfter : \n" +
                LinkedListHelper.displayList(problem.swapPairsI(head));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private SingleLinkedListNode swapPairsR(SingleLinkedListNode p1) {
        if (p1 == null || p1.next == null)
            return p1;

        SingleLinkedListNode p2 = p1.next;
        SingleLinkedListNode p3 = p2.next;

        p1.next = p3;
        p2.next = p1;

        if (p3 != null)
            p1.next = swapPairsR(p3);

        return p2;
    }

    private SingleLinkedListNode swapPairsI(SingleLinkedListNode head) {
        if (head == null || head.next == null)
            return head;

        SingleLinkedListNode prev = null, curr = head, next = null;

        while (curr.next != null) {
            next = curr.next;

            curr.next = prev;

            prev = curr;
            curr = next;
        }

        head = null;

        return head;
    }
}
