package com.dl.text4;

import com.dl.util.TreeNode;

public class FindTilt {

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 递归求得每个结点的坡度
        return Math.abs(getSum(root.left) - getSum(root.right)) 
        + findTilt(root.left) + findTilt(root.right);
    }

    private int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 递归求得子树总和
        return root.val + getSum(root.left) + getSum(root.right); 
    }
	
}
