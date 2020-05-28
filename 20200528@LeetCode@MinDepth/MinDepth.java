package com.dl.test6;

import com.dl.util.TreeNode;

public class MinDepth {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // null�ڵ㲻����Ƚ�
        if (root.left == null && root.right != null) {
            return 1 + minDepth(root.right);
        }
        // null�ڵ㲻����Ƚ�
        if (root.right == null && root.left != null) {
            return 1 + minDepth(root.left);
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
    
}
