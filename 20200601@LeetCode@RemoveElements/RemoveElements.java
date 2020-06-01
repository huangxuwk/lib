package com.dl.test7;

import com.dl.util.ListNode;

public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            if (cur.val == val) {
                next = cur.next;
                cur.next = null;
                if (pre != null) {
                    pre.next = next;
                }
                if (pre == null) {
                    head = next;
                }
                cur = next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
	
}
