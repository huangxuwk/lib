package com.dl.test3;

import com.dl.test1.Mirror.TreeNode;

public class IsBalanced {
	
	public IsBalanced() {
	}
	
    public boolean IsBalanced_Solution(TreeNode root) {
        if (check(root, 0) == -1) {
            return false;
        }
        return true;
    }
    
    private int check(TreeNode root, int high) {
        if (root == null) {
            return high;
        }
        high++;
        int left = check(root.left, high);
        int right = check(root.right, high);
        if (left == -1 || right == -1) {
            return -1;
        } else {
            int detla = left - right;
            if (Math.abs(detla) > 1) {
                return -1;
            } else {
                return Math.max(left, right);
            }
        }
    }
}
