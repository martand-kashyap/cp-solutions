package linkedlist.single;

import java.io.PrintWriter;

class SwapNodesInGroupsOfK {
    public static void main(String[] args) {
        int k = 5;
        int[] nodeValues = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        SingleLinkedListNode head = new SingleLinkedListNode(10, null);
        for (int i = 1; i < nodeValues.length; i++) {
            LinkedListHelper.insertLast(nodeValues[i], head);
        }

        SwapNodesInGroupsOfK problem = new SwapNodesInGroupsOfK();
        String res = "Before : \n" +
                LinkedListHelper.displayList(head) + "\nAfter (k=" + k + "): \n" +
                LinkedListHelper.displayList(problem.solveR(head, k));

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private SingleLinkedListNode solveR(SingleLinkedListNode head, int k) {
        return null;
    }

    private SingleLinkedListNode solveI(SingleLinkedListNode head, int k) {
        return null;
    }
}
