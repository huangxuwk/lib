package com.dl.test5;

import java.util.Stack;

import com.dl.util.TreeNode;

public class KthNode {

	public KthNode() {
	}
	
    TreeNode kthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k < 1) {
            return null;
        }
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = pRoot;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (++count == k) {
                    return node;
                }
                node = node.right;
            }
        }
        return null;
    }
}
