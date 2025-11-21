package linkedlist.single;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UnionLL {
    /*
    Given two linked lists, return the union of two linked lists.
    This union should include all the distinct elements only.

    Example 1:
    Input:
    L1 = 9->6->4->2->3->8
    L2 = 1->2->8->6->2
    Output: 1 2 3 4 6 8 9
     */
    public static void main(String[] args) {
        int[] ll1Elements = {6, 6, 8, 4, 4, 4, 6, 2};
        int[] ll2Elements = {13, 5};

        SingleLinkedListNode ll1Head = new SingleLinkedListNode(ll1Elements[0], null);
        for (int i = 1; i < ll1Elements.length; i++) {
            LinkedListHelper.insertLast(ll1Elements[i], ll1Head);
        }

        SingleLinkedListNode ll2Head = new SingleLinkedListNode(ll2Elements[0], null);
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

    public SingleLinkedListNode findUnion(SingleLinkedListNode head1, SingleLinkedListNode head2) {
        SingleLinkedListNode dummy = new SingleLinkedListNode();
        List<Integer> s = new ArrayList<>();
        SingleLinkedListNode r = dummy;

        for (SingleLinkedListNode i = head1; i != null; i = i.next) {
            if (!s.contains(i.val))
                s.add(i.val);
        }

        for (SingleLinkedListNode i = head2; i != null; i = i.next) {
            if (!s.contains(i.val))
                s.add(i.val);
        }

        Collections.sort(s);

        for (int i = 0; i < s.size(); i++) {
            r.next = new SingleLinkedListNode(s.get(i), null);
            r = r.next;
        }

        return dummy.next;
    }
}
