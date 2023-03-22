package linkedlist.single;

import java.io.PrintWriter;

class SwapNodesInPairs {
    public static void main(String[] args) {
        SingleLinkedListNode head = new SingleLinkedListNode(10, null);
        head.next = new SingleLinkedListNode(20, null);
        head.next.next = new SingleLinkedListNode(30, null);
        head.next.next.next = new SingleLinkedListNode(40, null);
        head.next.next.next.next = new SingleLinkedListNode(50, null);


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

    private SingleLinkedListNode swapPairsI(SingleLinkedListNode p1) {
        if (p1 == null || p1.next == null)
            return p1;

        SingleLinkedListNode p2 = p1.next;
        SingleLinkedListNode p3 = p2.next;
        
        SingleLinkedListNode t = p1;
        p1.next = p2;
        p2.next = t;

        return p2;
    }
}
