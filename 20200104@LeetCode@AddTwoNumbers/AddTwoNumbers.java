package com.dl.test;

import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	List<Integer> listOne = new ArrayList<Integer>();
    	List<Integer> listTwo = new ArrayList<Integer>();
    	ListNode result = null;
    	
    	ListNode next = l1.next;
    	listOne.add(l1.val);
    	while (next != null) {
    		listOne.add(next.val);
    		next = next.next;
    	}
    	
    	next = l2.next;
    	listTwo.add(l2.val);
    	while (next != null) {
    		listTwo.add(next.val);
    		next = next.next;
    	}
    	int sizeOne = listOne.size();
    	int sizeTwo = listTwo.size();
    	int length = sizeOne >= sizeTwo ? sizeOne : sizeTwo;
    	int sum;
    	int high = 0;
    	for (int i = 0; i < length; i++) {
    		sum = high;
    		if (i < sizeOne) {
    			sum += listOne.get(i);
    		}
    		if (i < sizeTwo) {
    			sum += listTwo.get(i);
    		}	
    		high = sum / 10;
    		sum = sum - high * 10;
    		if (result == null) {
    			result = new ListNode(sum);
    			next = result;
    		} else {
        		ListNode node = new ListNode(sum);
        		next.next = node;
        		next = next.next;
    		}
		}
    	if (high != 0) {
    		if (result == null) {
    			result = new ListNode(high);
    			next = result;
    		} else {
        		ListNode node = new ListNode(high);
        		next.next = node;
    		}
    	}
    	
		return result;
    }
    
    class ListNode {
    	int val;
    	ListNode next;
    	ListNode(int x) { val = x; }
    }
    
    static class Test {
    	public static void main(String[] args) {
    		AddTwoNumbers add = new AddTwoNumbers();
    		ListNode l1 = add.new ListNode(5);
//    		l1.next = add.new ListNode(4);
//    		l1.next.next = add.new ListNode(3);
    		ListNode l2 = add.new ListNode(5);
//    		l2.next = add.new ListNode(6);
//    		l2.next.next = add.new ListNode(4);
    		
    		ListNode result = add.addTwoNumbers(l1, l2);
    		System.out.println(result.val);
        	ListNode next = result.next;
        	while (next != null) {
        		System.out.println(next.val);
        		next = next.next;
        	}
		}
    }
}
