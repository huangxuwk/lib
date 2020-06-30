package com.dl.text7;

import com.dl.util.TreeNode;

public class LongestUnivaluePath {
    private int res;
    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        maxLength(root);
        return res;

    }
    //����������������Ƚڵ�������ֵ
    public int maxLength(TreeNode root){
        if(root == null) return 0;
        //�ݹ�ѹջ
        int left = maxLength(root.left);
        int right = maxLength(root.right);
        //Ҷ�ӽڵ���д���
        int leftLength = 0;
        int rightLength = 0;
        //������ ����ڵ���ͬ�ټ���
        if(root.left != null && root.left.val == root.val){
            leftLength = left + 1;
        }
        //������ ����ڵ���ͬ�ټ���
        if(root.right != null && root.right.val == root.val){
            rightLength = right + 1;
        }
        //�жϵ�ǰ����ȵ���Ľڵ㳤��
        res = Math.max(res,rightLength+leftLength);
        //�������������������Ǹ�����
        return Math.max(leftLength,rightLength);
    }
}
