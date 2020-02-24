package com.dl.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class GetLeastNumbers {

	public GetLeastNumbers() {
	}
	
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
    	ArrayList<Integer> result = new ArrayList<>();
        if (input == null || k <= 0 || input.length < k) {
        	return result;
        }
        
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, Collections.reverseOrder());
        int length = input.length;
        int temp;
        for (int index = 0; index < length; index++) {
        	temp = input[index];
        	if (index < k) {
        		heap.offer(temp);
        	} else {
        		if (temp < heap.peek()) {
        			heap.poll();
        			heap.offer(temp);
        		}
        	}
        }
        while (!heap.isEmpty()) {
        	result.add(heap.poll());
        }
        
        return result;
    }
}
