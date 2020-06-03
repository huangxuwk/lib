package com.dl.test8;

import com.dl.util.ListNode;

public class DeleteNode {
	
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    
}
