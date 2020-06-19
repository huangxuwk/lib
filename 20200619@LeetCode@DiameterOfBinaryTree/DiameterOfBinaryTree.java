package com.dl.text3;

import com.dl.util.TreeNode;

public class DiameterOfBinaryTree {
    private int max;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 直径为左子树叶子节点到右子树叶子节点的长度
        max = Math.max(left + right, max);
        // 计算左右子树中的最长长度
        return Math.max(left, right) + 1;
    }
}
