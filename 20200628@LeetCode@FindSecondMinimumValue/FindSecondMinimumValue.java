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
        // �����ǰ�����ڸ��ڵ㣬��ô��ǰ�����ǵڶ�С�Ľ��
        if (root.val > minVal) {
            return root.val;
        }
        int left = findValue(root.left, minVal);
        int right = findValue(root.right, minVal);
        // �����ӽڵ㶼��Ϊnull����ȡ��С��
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else {
            return Math.max(left, right);
        }
    }
	
}
