package com.dl.test;

import java.util.Arrays;

/*
 * ����ĳ��������ǰ���������������Ľ�������ؽ����ö����������������ǰ���������������Ľ���ж������ظ������֡�
 * ��������ǰ���������{1,2,4,7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}�����ؽ������������ء�
 */
public class RebuildTree {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        int length = pre.length;
        for (int index = 0; index < length; index++) {
            if (root.val == pre[0]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, index + 1), Arrays.copyOfRange(in, 0, index));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, index + 1, length), Arrays.copyOfRange(in, index, length));                
                break;
            }
        }
        return root;
    }
    
    public static void main(String[] args) {
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] in = {4,7,2,1,5,3,8,6};
		new RebuildTree().reConstructBinaryTree(pre, in);
	}
    
 class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
