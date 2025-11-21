package linkedlist.single;

import java.io.PrintWriter;

class Add2Numbers {
    /*
    Given two decimal numbers represented by two linked lists of size N and M respectively.
    The task is to return a linked list that represents the sum of these two numbers.

    For example,
    the number 190 will be represented by the linked list, 1->9->0->null,
    similarly 25 by 2->5->null.
    Sum of these two numbers is 190 + 25 = 215,
    which will be represented by 2->1->5->null.

    You are required to return the head of the linked list 2->1->5->null.

    Example 1
    Input: l1 = [4,5], l2 = [3,4,5]
    Output: [3,9,0]
    Explanation: 45 + 345= 390.

    Example 3:
    Input: l1 = [6,3], l2 = [7]
    Output: [7,0]
     */
    public static void main(String[] args) {
        int[] ll1Elements = {4, 5};
        int[] ll2Elements = {3, 4, 5};

        SingleLinkedListNode ll1Head = new SingleLinkedListNode(ll1Elements[0], null);
        for (int i = 1; i < ll1Elements.length; i++) {
            LinkedListHelper.insertLast(ll1Elements[i], ll1Head);
        }

        SingleLinkedListNode ll2Head = new SingleLinkedListNode(ll2Elements[0], null);
        for (int i = 1; i < ll2Elements.length; i++) {
            LinkedListHelper.insertLast(ll2Elements[i], ll2Head);
        }

        Add2Numbers problem = new Add2Numbers();
        String res = "Initial Linked Lists are: -" + "\n" +
                LinkedListHelper.displayList(ll1Head) +
                LinkedListHelper.displayList(ll2Head) + "\n" +
                "Sum of the above :\n" +
                LinkedListHelper.displayList(problem.add(ll1Head, ll2Head));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private SingleLinkedListNode add(SingleLinkedListNode num1Head, SingleLinkedListNode num2Head) {
        SingleLinkedListNode dummy = new SingleLinkedListNode(-1, null), i = dummy;

        //reverse both the LL, as addition starts from the units place
        ReverseLL rev = new ReverseLL();
        SingleLinkedListNode num1Rev = rev.reverseListI(num1Head), num2Rev = rev.reverseListI(num2Head);

        int carry = 0;
        while (num1Rev != null || num2Rev != null || carry != 0) {
            int x = num1Rev != null ? num1Rev.val : 0;
            int y = num2Rev != null ? num2Rev.val : 0;

            int tempSum = x + y + carry;

            carry = tempSum / 10;
            i.next = new SingleLinkedListNode(tempSum % 10, null);
            i = i.next;

            if (num1Rev != null) {
                num1Rev = num1Rev.next;
            }
            if (num2Rev != null) {
                num2Rev = num2Rev.next;
            }
        }

        //the sum is calculated in reverse, rev(rev(sum)) = sum
        return rev.reverseListI(dummy.next);
    }
}
