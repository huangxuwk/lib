package com.dl.test;

import java.util.ArrayList;

/*
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class PrintListnode {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    	ArrayList<Integer> list = new ArrayList<>();
    	ArrayList<Integer> result = new ArrayList<>();
    	
    	// 切记要判断边界条件
    	if (listNode == null) {
    		return result;
    	}
    	
    	while (listNode.next != null) {
    		list.add(listNode.val);
    		listNode = listNode.next;
    	}
    	list.add(listNode.val);
    	
    	int length = list.size();
    	for (int index = length - 1; index >= 0; index--) {
    		result.add(list.get(index));
    	}
    	
		return result;
    }
}

class ListNode {
       int val;
       ListNode next = null;

       ListNode(int val) {
           this.val = val;
       }
}
