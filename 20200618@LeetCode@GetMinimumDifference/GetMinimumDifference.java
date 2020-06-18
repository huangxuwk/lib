package com.dl.text3;

import com.dl.util.TreeNode;

public class GetMinimumDifference {
    private TreeNode preNode;
    private int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        getMin(root);
        return min;
    }

    private void getMin(TreeNode root) {
        if (root == null) {
            return;
        }
        getMin(root.left);
        if (preNode != null) {
            int dul = root.val - preNode.val;
            if (dul < min) {
                min = dul;
            }
        }
        preNode = root;
        getMin(root.right);
    }
}
