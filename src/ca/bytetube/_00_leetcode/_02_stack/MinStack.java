package ca.bytetube._00_leetcode._02_stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/description/
 *
 * @author dal
 */
class MinStack {

    private static class Node {
        int val;
        Node next;
        int min;

        public Node(int val, Node next, int min) {
            this.val = val;
            this.next = next;
            this.min = min;
        }
    }

    Node head;

    //    private Stack<Integer> dataStack;
//    private Stack<Integer> minStack;
//
//    public MinStack() {
//        dataStack = new Stack<>();
//        minStack = new Stack<>();
//    }
//
//    public void push(int val) {
//        dataStack.push(val);
//        if (minStack.isEmpty()) minStack.push(val);
//        else if (val <= minStack.peek()) minStack.push(val);
//
//    }
//
//    public void pop() {
//        int pop = 0;
//        if (!dataStack.isEmpty()) pop = dataStack.pop();
//        if (pop == minStack.peek()) minStack.pop();
//
//    }
//
//    public int top() {
//        return dataStack.peek();
//    }
//
//    public int getMin() {
//        if (minStack.isEmpty()) throw new RuntimeException("stack is empty!");
//        return minStack.peek();
//    }
    public MinStack() {
        head = new Node(0, null, Integer.MAX_VALUE);
    }

    public void push(int val) {
        head = new Node(val, head, Math.min(head.min, val));

    }

    public void pop() {
        head = head.next;

    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}

