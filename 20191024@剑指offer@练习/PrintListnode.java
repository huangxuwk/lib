package com.dl.test;

import java.util.ArrayList;

/*
 * ����һ�������������β��ͷ��˳�򷵻�һ��ArrayList��
 */
public class PrintListnode {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    	ArrayList<Integer> list = new ArrayList<>();
    	ArrayList<Integer> result = new ArrayList<>();
    	
    	// �м�Ҫ�жϱ߽�����
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
