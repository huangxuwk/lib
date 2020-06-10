package com.dl.test10;

import com.dl.util.TreeNode;

public class SumOfLeftLeaves {
    private int sum;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumOf(root, false);
        return sum;
    }

    private void sumOf(TreeNode root, boolean left) {
        if (root.left == null && root.right == null && left) {
            sum += root.val;
        }
        if (root.left != null) {
            sumOf(root.left, true);
        }
        if (root.right != null) {
            sumOf(root.right, false);
        }
    }
}
