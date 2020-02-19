package com.dl.test1;

import java.util.ArrayList;

import com.dl.test1.Mirror.TreeNode;

public class PrintFromTopToBottom {

	public PrintFromTopToBottom() {
	}
	
	// 从上至下，每层从左至右打印二叉树
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<TreeNode> queue = new ArrayList<>();
        if (root == null) {
            return list;
        }
        queue.add(root);
        while (queue.size() != 0) {
            TreeNode temp = queue.remove(0);
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            list.add(temp.val);
        }
        return list;        
    }
}
