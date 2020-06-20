package com.dl.text4;

import com.dl.util.TreeNode;

public class FindTilt {

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // �ݹ����ÿ�������¶�
        return Math.abs(getSum(root.left) - getSum(root.right)) 
        + findTilt(root.left) + findTilt(root.right);
    }

    private int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // �ݹ���������ܺ�
        return root.val + getSum(root.left) + getSum(root.right); 
    }
	
}
