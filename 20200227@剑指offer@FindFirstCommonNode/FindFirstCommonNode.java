package com.dl.test2;

import java.util.HashSet;

public class FindFirstCommonNode {

	public FindFirstCommonNode() {
	}
	
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        HashSet<ListNode> nodeSet = new HashSet<>();
        while (pHead1 != null) {
            nodeSet.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            if (nodeSet.contains(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }
    
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
