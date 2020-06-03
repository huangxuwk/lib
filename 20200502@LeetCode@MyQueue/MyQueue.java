package com.dl.test8;

import java.util.Stack;

public class MyQueue {
    private Stack<Integer> stack;
    private Stack<Integer> temp;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        temp = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        int size = stack.size();
        while (size-- > 0) {
            temp.push(stack.pop());
        }
        stack.push(x);
        size = temp.size();
        while (size-- > 0) {
            stack.push(temp.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}
