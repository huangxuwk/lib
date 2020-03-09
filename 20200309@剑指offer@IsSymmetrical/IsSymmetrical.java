package com.dl.test4;

import com.dl.util.TreeNode;

public class IsSymmetrical {
	
	public IsSymmetrical() {
	}
	
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }    
        return compare(pRoot.left, pRoot.right);
    }
    
    boolean compare(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        
        return compare(left.right, right.left) && compare(left.left, right.right);
    }
}
