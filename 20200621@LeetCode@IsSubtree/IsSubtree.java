package com.dl.text4;

import com.dl.util.TreeNode;

public class IsSubtree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) {
            return false;
        }
        // ����s������ÿһ���ڵ㶼��t��ȣ����Ƿ�Ϊ��ͬ��
        return isSubtree(s.left, t) || isSubtree(s.right, t) || subtree(s, t);
    }

    private boolean subtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.val == t.val && subtree(s.left, t.left) && subtree(s.right, t.right);
    }
	
}
