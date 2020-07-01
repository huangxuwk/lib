package com.dl.text8;

import java.util.PriorityQueue;

public class KthLargest {
    private PriorityQueue<Integer> queue;
    private int size;

    public KthLargest(int k, int[] nums) {
    	queue = new PriorityQueue<>(k);
    	size = k;
        for (int n : nums) {
            add(n);
        }
    }
    
    public int add(int val) {
    	if (queue.size() < size) {
    		queue.offer(val);
    		return queue.peek();
    	} 
        if (val > queue.peek()) {
        	queue.poll();
        	queue.offer(val);
        }
        return queue.peek();
    }
}
