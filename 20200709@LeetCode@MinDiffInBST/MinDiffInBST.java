package com.dl.textJ;

import java.util.Stack;

import com.dl.util.TreeNode;

public class MinDiffInBST {

    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        int pre = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if (pre != Integer.MIN_VALUE) {
                    if (p.val - pre < min) {
                        min = p.val - pre;
                    }
                }
                pre = p.val;
                p = p.right;
            }
        }
        return min;
    }
	
}
