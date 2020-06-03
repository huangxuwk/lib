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
        // 如果两个结点都大于当前根节点，说明两个结点都在当前结点的右子树上
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
	
}
