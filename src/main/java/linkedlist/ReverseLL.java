package linkedlist;

class ReverseLL {
    public static void main(String[] args) {
        ListNode head = new ListNode(10);
        head.next = new ListNode(20);
        head.next.next = new ListNode(30);

        ReverseLL rev = new ReverseLL();
        head = rev.reverseList(head);

        LLHelper.print(head);
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode smaller = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return smaller;
    }
}
