package linkedlist;

class ReverseLL {
    public static void main(String[] args) {

    }

    private ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode smaller = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return smaller;
    }
}
