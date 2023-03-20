package linkedlist;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UnionLL {
    public static void main(String[] args) {
        int[] ll1Elements = {6, 6, 8, 4, 4, 4, 6, 2};
        int[] ll2Elements = {13, 5};

        ListNode ll1Head = new ListNode(ll1Elements[0], null);
        for (int i = 1; i < ll1Elements.length; i++) {
            LinkedListHelper.insertLast(ll1Elements[i], ll1Head);
        }

        ListNode ll2Head = new ListNode(ll2Elements[0], null);
        for (int i = 1; i < ll2Elements.length; i++) {
            LinkedListHelper.insertLast(ll2Elements[i], ll2Head);
        }

        UnionLL problem = new UnionLL();
        String res = "Initial Linked Lists are: -" + "\n" +
                LinkedListHelper.displayList(ll1Head) +
                LinkedListHelper.displayList(ll2Head) + "\n" +
                "Union of the above :\n" +
                LinkedListHelper.displayList(problem.findUnion(ll1Head, ll2Head));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    public ListNode findUnion(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode();
        List<Integer> s = new ArrayList<>();
        ListNode r = dummy;

        for (ListNode i = head1; i != null; i = i.next) {
            if (!s.contains(i.val))
                s.add(i.val);
        }

        for (ListNode i = head2; i != null; i = i.next) {
            if (!s.contains(i.val))
                s.add(i.val);
        }

        Collections.sort(s);

        for (int i = 0; i < s.size(); i++) {
            r.next = new ListNode(s.get(i), null);
            r = r.next;
        }

        return dummy.next;
    }
}
