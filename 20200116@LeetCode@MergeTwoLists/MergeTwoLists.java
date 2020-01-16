package com.dl.test2;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoLists {
	
	public MergeTwoLists() {
	}
	
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		List<Integer> list = new ArrayList<Integer>();
		ListNode next1 = l1;
		ListNode next2 = l2;
		
		while (next1 != null && next2 != null) {
			if (next1.val < next2.val) {
				list.add(next1.val);
				next1 = next1.next;
			} else {
				list.add(next2.val);
				next2 = next2.next;
			}
		}
		ListNode head = null;
		ListNode tail = null;
		for (Integer integer : list) {
			if (head == null) {
				head = new ListNode(integer);
				tail = head;
			} else {
				tail.next = new ListNode(integer);
				tail = tail.next;
			}
		}
		
		if (next1 != null) {
			if (head == null) {
				head = next1;
			} else {
				tail.next = next1;
			}
		} else if (next2 != null) {
			if (head == null) {
				head = next2;
			} else {
				tail.next = next2;
			}
		}
		
    	return head;
    }

	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
    static class test {
    	public static void main(String[] args) {
    		MergeTwoLists merge = new MergeTwoLists();
    		ListNode l1 = null;
    		ListNode l2 = null;
//    		ListNode l1 = merge.new ListNode(1);
//    		l1.next = merge.new ListNode(2);
//    		l1.next.next = merge.new ListNode(4);
//    		ListNode l2 = merge.new ListNode(1);
//    		l2.next = merge.new ListNode(3);
//    		l2.next.next = merge.new ListNode(4);
    		ListNode result = merge.mergeTwoLists(l1, l2);
    		while (result != null) {
    			System.out.println(result.val);
    			result = result.next;
    		}
		}
    }
     
}


