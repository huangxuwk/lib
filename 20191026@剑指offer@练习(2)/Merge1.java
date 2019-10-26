package com.dl.test2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.dl.test2.FindKthToTail1.ListNode;

/*
 * 输入两个单调递增的链表，输出两个链表合成后的链表，
 * 当然我们需要合成后的链表满足单调不减规则。
 */
public class Merge1 {
	
    public ListNode Merge(ListNode list1,ListNode list2) {
    	if (list1 == null && list2 == null) {
    		return null;
    	}
    	if (list1 == null) {
    		return list2;
    	}
    	if (list2 == null) {
    		return list1;
    	}
    	List<ListNode> nodeList = new ArrayList<>();
    	while (list1.next != null) {
    		nodeList.add(list1);
    		list1 = list1.next;
    	}
    	nodeList.add(list1);
    	while (list2.next != null) {
    		nodeList.add(list2);
    		list2 = list2.next;
    	}
    	nodeList.add(list2);
    	nodeList.sort(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
    	
    	ListNode temp = null;
    	for (int i = 0; i < nodeList.size(); i++) {
    		if (i == 0) {
    			list2 = nodeList.get(i);	
    			temp = list2;
    			temp.next = null;
    			
    			continue;
    		}
    		temp.next = nodeList.get(i);
    		temp = temp.next;
    		temp.next = null;
    	}
		return list2;
    }
    
    public static void main(String[] args) {
    	FindKthToTail1 f = new FindKthToTail1();
		ListNode list1 = f.new ListNode(1);
		list1.next = f.new ListNode(3);
		list1.next.next = f.new ListNode(5);
		list1.next.next.next = f.new ListNode(7);
		
		ListNode list2 = f.new ListNode(2);
		list2.next = f.new ListNode(4);
		list2.next.next = f.new ListNode(6);
		list2.next.next.next = f.new ListNode(8);

		list2 = new Merge1().Merge(list1, list2);
    	while (list2.next != null) {
    		System.out.println(list2.val);
    		list2 = list2.next;
    	}
    	System.out.println(list2.val);
	}
}
