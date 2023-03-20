package linkedlist;

class LinkedListHelper {
    static String displayList(ListNode head) {
        StringBuilder output = new StringBuilder();

        while (head != null) {
            output.append(head.val).append("->");
            head = head.next;
        }

        output.append("X").append("\n");
        return output.toString();
    }

    static int getLengthR(ListNode head) {
        if (head == null) {
            return 0;
        }

        return 1 + getLengthR(head.next);
    }

    static int getLengthI(ListNode head) {
        if (head == null) {
            return 0;
        }

        int length = 0;
        while (head != null) {
            length += 1;
            head = head.next;
        }

        return length;
    }

    static ListNode insertLast(int data, ListNode head) {
        if (head == null) {
            return new ListNode(data, null);
        }

        ListNode c = head;
        while (c.next != null) {
            c = c.next;
        }

        c.next = new ListNode(data, null);

        return head;
    }
}
