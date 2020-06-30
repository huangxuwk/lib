package com.dl.text7;

import com.dl.util.TreeNode;

public class LongestUnivaluePath {
    private int res;
    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        maxLength(root);
        return res;

    }
    //返回左右子树中相等节点距离最长的值
    public int maxLength(TreeNode root){
        if(root == null) return 0;
        //递归压栈
        int left = maxLength(root.left);
        int right = maxLength(root.right);
        //叶子节点进行处理
        int leftLength = 0;
        int rightLength = 0;
        //左子树 与根节点相同再加入
        if(root.left != null && root.left.val == root.val){
            leftLength = left + 1;
        }
        //右子树 与根节点相同再加入
        if(root.right != null && root.right.val == root.val){
            rightLength = right + 1;
        }
        //判断当前的相等的最长的节点长度
        res = Math.max(res,rightLength+leftLength);
        //返回左右子树中最大的那个子树
        return Math.max(leftLength,rightLength);
    }
}
