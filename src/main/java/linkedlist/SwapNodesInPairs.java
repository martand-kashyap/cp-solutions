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
        output.append(LLHelper.print(head));

        SwapNodesInPairs snp = new SwapNodesInPairs();
        head = snp.swapPairsR(head);

        output.append("After :\n");
        output.append(LLHelper.print(head));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(output);
        pw.close();
    }

    private ListNode swapPairsR(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode p2 = head.next;
        ListNode p3 = p2.next;

        head.next = p3;
        p2.next = head;

        if (p3 != null)
            head.next = swapPairsR(p3);

        return p2;
    }
}
