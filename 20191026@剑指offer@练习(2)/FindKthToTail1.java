package com.dl.test2;

import java.util.ArrayList;
import java.util.List;

/*
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class FindKthToTail1 {

    public ListNode FindKthToTail(ListNode head,int k) {
    	if (head == null) {
    		return null;
    	}
    	if (k <= 0) {
    		return null;
    	}
    	List<ListNode> nodeList = new ArrayList<>();
    	while (head.next != null) {
    		nodeList.add(head);
    		head = head.next;
    	}
    	nodeList.add(head);
    	// 防止超出
    	if (k > nodeList.size()) {
    		return null;
    	}
    	
		return nodeList.get(nodeList.size() - k);
    }
    
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    
    public static void main(String[] args) {
    	FindKthToTail1 f = new FindKthToTail1();
		ListNode head = f.new ListNode(1);
		head.next = f.new ListNode(2);
		head.next.next = f.new ListNode(3);
		head.next.next.next = f.new ListNode(4);
		
		System.out.println(f.FindKthToTail(head, 1).val);
	}
}
