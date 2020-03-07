package com.dl.test4;

import java.util.HashSet;

import com.dl.test2.FindFirstCommonNode.ListNode;

public class EntryNodeOfLoop {

	public EntryNodeOfLoop() {
	}
	
	public ListNode entryNodeOfLoop(ListNode pHead) {
	    if (pHead == null) {
	        return null;
	    }
	    HashSet<ListNode> set = new HashSet<>();
	    while (pHead != null) {
	        if (set.contains(pHead)) {
	            return pHead;
	        } else {
	            set.add(pHead);
	            pHead = pHead.next;
	        }
	    }
	    
	    return null;
	}
}
