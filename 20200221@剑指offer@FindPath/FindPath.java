package com.dl.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.dl.test1.Mirror.TreeNode;

public class FindPath {
	private ArrayList<ArrayList<Integer>> res = new ArrayList<>();
	private ArrayList<Integer> path = new ArrayList<>();
	
	public FindPath() {
	}
	
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
    	if (root == null || target == 0) {
    		return null;
    	}
    	getPath(root, target);
    	Collections.sort(res, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return -(o1.size() - o2.size());
			}
		});
    	
		return res;
    }
    
    public void getPath(TreeNode node, int target) {
    	if (target < 0) {
    		return;
    	}
    	path.add(node.val);
    	if (target == node.val && node.left == null && node.right == null) {
    		// 如果不新建，则最后的res中的列表都是同一个
    		res.add(new ArrayList<>(path));
    	}
    	if (node.left != null) {
    		getPath(node.left, target - node.val);
    	}
    	if (node.right != null) {
    		getPath(node.right, target - node.val);
    	}
    	// 删除最后一个结点，回退到树的上一层
    	// 比如左结点找完，需要回退到node层，再找node.right
    	path.remove(path.size() - 1);
    }
}
