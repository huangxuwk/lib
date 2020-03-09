package com.dl.test4;

import com.dl.util.TreeLinkNode;

public class GetNext {

	public GetNext() {
	}
	
    public TreeLinkNode getNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        // 如果有右子树
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (pNode.next != null) {
            if (pNode.next.left == pNode) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }
}
