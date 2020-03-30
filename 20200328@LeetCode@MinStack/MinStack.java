package com.dl.test4;

import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {
	private PriorityQueue<Integer> heap;
	private Stack<Integer> stack;
	
	public MinStack() {
		heap = new PriorityQueue<>();
		stack = new Stack<>();
	}
	
    public void push(int x) {
    	stack.push(x);
    	heap.offer(x);
    }
    
    public void pop() {
    	heap.remove(stack.pop());
    }
    
    public int top() {
    	return stack.peek();
    }
    
    public int getMin() {
    	return heap.peek();
    }
}
