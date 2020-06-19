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
        // ֱ��Ϊ������Ҷ�ӽڵ㵽������Ҷ�ӽڵ�ĳ���
        max = Math.max(left + right, max);
        // �������������е������
        return Math.max(left, right) + 1;
    }
}
