package com.dl.test2;

import com.dl.test1.Mirror.TreeNode;

public class TreeDepth {
    private int treeDepth;

    public TreeDepth() {
	}
	
    public int treeDepth(TreeNode root) {
        check(root, 0);
        
        return treeDepth;
    }
    
    private void check(TreeNode root, int high) {
        if (root == null) {
            return;
        }
        high++;
        treeDepth = high > treeDepth ? high : treeDepth;
        check(root.left, high);
        check(root.right, high);
    }
}
