package com.dl.test;

import java.util.Stack;

/*
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class StackForQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
    }
    
    public int pop() {
    	return stack2.pop();
    }
    
    public static void main(String[] args) {
		StackForQueue queue = new StackForQueue();
	
		queue.push(1);
		queue.push(2);
		queue.push(3);
		
		System.out.println(queue.pop());
	}
}
