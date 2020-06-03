package com.dl.test8;

import com.dl.util.TreeNode;

public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val == root.val) {
            return p;
        }
        if (q.val == root.val) {
            return q;
        }
        // ���������㶼���ڵ�ǰ���ڵ㣬˵��������㶼�ڵ�ǰ������������
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
	
}
