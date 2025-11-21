package linkedlist.single;

import java.io.PrintWriter;
import java.util.Stack;

class IsLLPalindrome {
    public static void main(String[] args) {
        int[] nodes = {1, 2, 4, 2, 1};

        SingleLinkedListNode ll1Head = new SingleLinkedListNode(nodes[0], null);
        for (int i = 1; i < nodes.length; i++) {
            LinkedListHelper.insertLast(nodes[i], ll1Head);
        }

        IsLLPalindrome problem = new IsLLPalindrome();
        String res = "The Linked Lists : -" + "\n" +
                LinkedListHelper.displayList(ll1Head) +
                "is a palindrome? " + "\n\n" +
                "Using Stack : " + problem.usingStack(ll1Head) + "\n" +
                "In Place (using reverse) : " + problem.byReversing(ll1Head);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private boolean usingStack(SingleLinkedListNode ll1Head) {
        int length = LinkedListHelper.getLengthR(ll1Head), i = 0;

        Stack<SingleLinkedListNode> stack = new Stack<>();
        SingleLinkedListNode current = ll1Head;
        while (i < length / 2) {
            stack.push(current);
            current = current.next;
            i++;
        }

        if (length % 2 == 1) {
            current = current.next;
        }

        while (current != null) {
            if (stack.peek().val != current.val) {
                return false;
            }

            stack.pop();
            current = current.next;
        }

        return stack.isEmpty();
    }

    private boolean byReversing(SingleLinkedListNode head) {
        //find the mid node, T(n) = O(n)
        SingleLinkedListNode part1End = LinkedListHelper.findMidNodeUsingFloydCycleFinding(head);
        SingleLinkedListNode part2Start = part1End.next;

        //disconnect the 2 parts
        part1End.next = null;

        //reverse the 2nd half, T(n) = O(n)
        ReverseLL reverseLL = new ReverseLL();
        part2Start = reverseLL.reverseListR(part2Start);

        //compare the two lists, T(n) = O(n)
        SingleLinkedListNode i = head, j = part2Start;
        while (i != null && j != null) {
            if (i.val != j.val) {
                return false;
            }
            i = i.next;
            j = j.next;
        }

        //restore the original list, T(n) = O(n)
        part1End.next = reverseLL.reverseListR(part2Start);

        //if the control reaches here, then the LL is a palindrome, T(n) = O(n)
        return true;
        //T(n) = O(n)
    }
}
