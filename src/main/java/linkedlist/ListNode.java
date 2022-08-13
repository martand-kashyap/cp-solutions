package linkedlist;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class LLHelper {
    static String print(ListNode head) {
        StringBuffer output = new StringBuffer();

        while (head != null) {
            output.append(head.val).append("->");
            head = head.next;
        }
        output.append("\n");
        return output.toString();
    }
}