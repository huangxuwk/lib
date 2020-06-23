package com.dl.text5;

import com.dl.util.TreeNode;

public class MergeTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        merge(t1, t2, true, t1);
        return t1;
    }

    private void merge(TreeNode t1, TreeNode t2, boolean left, TreeNode parent) {
        if ((t1 == null && t2 == null) || (t1 != null && t2 == null)) {
            return;
        }
        if (t1 == null && t2 != null) {
            if (left) {
                parent.left = t2;
            } else {
                parent.right = t2;
            }
            return;
        }
        if (t1 != null && t2 != null) {
            t1.val += t2.val;
        }
        merge(t1.left, t2.left, true, t1);
        merge(t1.right, t2.right, false, t1);
    }
	
}
