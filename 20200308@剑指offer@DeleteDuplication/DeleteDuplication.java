package com.dl.test4;

import com.dl.util.ListNode;

public class DeleteDuplication {

	public DeleteDuplication() {
	}
	
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode pre = null;
        ListNode next = null;
        ListNode node = pHead;
        while (node != null) {
            next = node;
            while ((next = next.next) != null && node.val == next.val) {
            }
            if (next == node.next) {
                pre = node;   
            } else {
                if (pre == null) {
                    pHead = next;
                } else {
                    pre.next = next;
                }
                                  
            }
            node = next;
        }
        return pHead;
    }
    

    public static void main(String[] args) {
		ListNode pHead = new ListNode(1);
		pHead.next = new ListNode(1);
		pHead.next.next = new ListNode(2);
//		pHead.next.next.next = new ListNode(1);
//		pHead.next.next.next.next = new ListNode(4);
//		pHead.next.next.next.next.next = new ListNode(4);
//		pHead.next.next.next.next.next.next = new ListNode(5);
		
		DeleteDuplication de = new DeleteDuplication();
		ListNode node = de.deleteDuplication(pHead);
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
		
	}
}
