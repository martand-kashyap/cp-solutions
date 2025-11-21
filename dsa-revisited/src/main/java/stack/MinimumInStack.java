package stack;

import java.util.Stack;

class MinimumInStack {
    private final Stack<Integer> mainStack, minStack;

    /*-
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    Implement the MinStack class:

        MinStack() initializes the stack object.
        void push(int val) pushes the element val onto the stack.
        void pop() removes the element on the top of the stack.
        int top() gets the top element of the stack.
        int getMin() retrieves the minimum element in the stack.

    You must implement a solution with O(1) time complexity for each function.
     */
    public static void main(String[] args) {

    }

    public MinimumInStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        mainStack.push(val);

        if (minStack.isEmpty() || val <= minStack.peek())
            minStack.push(val);
    }

    public void pop() {
        if (mainStack.peek().equals(minStack.peek()))
            minStack.pop();

        mainStack.pop();
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

