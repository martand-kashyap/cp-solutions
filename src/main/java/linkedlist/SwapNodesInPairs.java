package linkedlist;

import java.io.PrintWriter;

class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(10, null);
        head.next = new ListNode(20, null);
        head.next.next = new ListNode(30, null);
        head.next.next.next = new ListNode(40, null);
        head.next.next.next.next = new ListNode(50, null);


        StringBuffer output = new StringBuffer();
        output.append("Before :\n");
        output.append(LinkedListHelper.displayList(head));

        SwapNodesInPairs snp = new SwapNodesInPairs();
        head = snp.swapPairsR(head);

        output.append("After :\n");
        output.append(LinkedListHelper.displayList(head));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(output);
        pw.close();
    }

    private ListNode swapPairsR(ListNode p1) {
        if (p1 == null || p1.next == null)
            return p1;

        ListNode p2 = p1.next;
        ListNode p3 = p2.next;

        p1.next = p3;
        p2.next = p1;

        if (p3 != null)
            p1.next = swapPairsR(p3);

        return p2;
    }

    private ListNode swapPairsI(ListNode p1) {
        if (p1 == null || p1.next == null)
            return p1;

        ListNode p2 = p1.next;
        ListNode p3 = p2.next;


        ListNode t = p1;
        p1.next = p2;
        p2.next = t;

        return p2;
    }
}
