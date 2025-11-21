package linkedlist.single;

class LinkedListHelper {
    static String displayList(SingleLinkedListNode head) {
        StringBuilder output = new StringBuilder();

        while (head != null) {
            output.append(head.val).append("->");
            head = head.next;
        }

        output.append("X").append("\n");
        return output.toString();
    }

    static int getLengthR(SingleLinkedListNode head) {
        if (head == null) {
            return 0;
        }

        return 1 + getLengthR(head.next);
    }

    static int getLengthI(SingleLinkedListNode head) {
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

    static SingleLinkedListNode insertLast(int data, SingleLinkedListNode head) {
        if (head == null) {
            return new SingleLinkedListNode(data, null);
        }

        SingleLinkedListNode c = head;
        while (c.next != null) {
            c = c.next;
        }

        c.next = new SingleLinkedListNode(data, null);

        return head;
    }

    static SingleLinkedListNode findMidNodeUsingFloydCycleFinding(SingleLinkedListNode head) {
        SingleLinkedListNode slow = head, fast = head;

        //find the middle node
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
