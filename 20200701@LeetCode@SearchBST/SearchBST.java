package com.dl.text8;

import java.util.Stack;

import com.dl.util.TreeNode;

public class SearchBST {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            if (n.val == val) {
                return n;
            }
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
        return null;
    }
	
}
