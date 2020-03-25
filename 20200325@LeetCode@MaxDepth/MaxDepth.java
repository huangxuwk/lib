package com.dl.test3;

import com.dl.util.TreeNode;

public class MaxDepth {
    private int high;

    public int maxDepth(TreeNode root) {
        getHigh(root, 0);

        return this.high;
    }

    private void getHigh(TreeNode root, int high) {
        if (root == null) {
            return;
        }
        if (++high > this.high) {
            this.high = high;
        }
        getHigh(root.left, high);
        getHigh(root.right, high);
    }
}
