package com.dl.test6;

import com.dl.util.TreeNode;

public class HasPathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val - sum == 0;
        }
        sum -= root.val;
        return hasPathSum(root.left, sum)
                || hasPathSum(root.right, sum);
    }
	
}
