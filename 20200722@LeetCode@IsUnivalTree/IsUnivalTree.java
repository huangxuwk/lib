package src.com.jd.leetcode.textE;

import src.com.jd.leetcode.util.TreeNode;

public class IsUnivalTree {
    private int value = -1;

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        value = root.val;
        return isUnival(root);
    }

    private boolean isUnival(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.val != value) {
            return false;
        }
        return isUnival(root.left) && isUnival(root.right);
    }
}
