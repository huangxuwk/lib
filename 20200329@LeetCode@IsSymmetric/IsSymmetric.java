package com.dl.test4;

import com.dl.util.TreeNode;

public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) {
            return true;
        }
        return dfs(root, root);
    }

    private boolean dfs(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val) && dfs(t1.left, t2.right) && dfs(t1.right, t2.left);
    }
}
