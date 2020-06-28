package com.dl.text7;

import com.dl.util.TreeNode;

public class FindSecondMinimumValue {

    public int findSecondMinimumValue(TreeNode root) {
        return findValue(root, root.val);
    }

    private int findValue(TreeNode root, int minVal) {
        if (root == null) {
            return -1;
        }
        // 如果当前结点大于根节点，那么当前结点就是第二小的结点
        if (root.val > minVal) {
            return root.val;
        }
        int left = findValue(root.left, minVal);
        int right = findValue(root.right, minVal);
        // 两个子节点都不为null，则取最小的
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else {
            return Math.max(left, right);
        }
    }
	
}
