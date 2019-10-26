package com.dl.test2;

import java.util.ArrayList;
import java.util.List;

import com.dl.test2.FindKthToTail1.ListNode;

/*
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseList1 {

    public ListNode ReverseList(ListNode head) {
    	if (head == null) {
    		return null;
    	}
    	List<ListNode> nodeList = new ArrayList<>();
    	while (head.next != null) {
    		nodeList.add(head);
    		head = head.next;
    	}
    	nodeList.add(head);
    	ListNode temp = null;
    	for (int i = nodeList.size() - 1; i >= 0; i--) {
    		if (i == nodeList.size() - 1) {
    			head = nodeList.get(i);	
    			temp = head;
    			temp.next = null;
    			
    			continue;
    		}
    		temp.next = nodeList.get(i);
    		temp = temp.next;
    		temp.next = null;
    	}
    	
		return head;
    }
    
    public static void main(String[] args) {
    	FindKthToTail1 f = new FindKthToTail1();
		ListNode head = f.new ListNode(1);
		head.next = f.new ListNode(2);
		head.next.next = f.new ListNode(3);
		head.next.next.next = f.new ListNode(4);
		
		head = new ReverseList1().ReverseList(head);
    	while (head.next != null) {
    		System.out.println(head.val);
    		head = head.next;
    	}
    	System.out.println(head.val);
	}
}
