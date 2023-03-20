package linkedlist;

import java.io.PrintWriter;

class ReverseLL {
    public static void main(String[] args) {
        ListNode head = new ListNode(10, null);
        head.next = new ListNode(20, null);
        head.next.next = new ListNode(30, null);


        StringBuffer output = new StringBuffer();
        output.append("Before :\n");
        output.append(LinkedListHelper.displayList(head));

        ReverseLL rev = new ReverseLL();
        head = rev.reverseListR(head);
        //head = rev.reverseListI(head);

        output.append("After :\n");
        output.append(LinkedListHelper.displayList(head));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(output);
        pw.close();
    }

    private ListNode reverseListR(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode smaller = reverseListR(head.next);
        head.next.next = head;
        head.next = null;

        return smaller;
    }

    private ListNode reverseListI(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }
}
