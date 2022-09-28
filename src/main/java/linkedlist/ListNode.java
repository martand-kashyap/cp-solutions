package linkedlist;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
class ListNode {
    int val;
    ListNode next;
}

class LLHelper {
    static String print(ListNode head) {
        StringBuilder output = new StringBuilder();

        while (head != null) {
            output.append(head.val).append("->");
            head = head.next;
        }
        output.append("\n");
        return output.toString();
    }
}