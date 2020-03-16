package com.dl.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * �ǵݹ����������
 * @author dl
 *
 */
public class Ergodic {

	public Ergodic() {
	}
	
	// ǰ�����
	public List<TreeNode> preorderTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		List<TreeNode> nodeList = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			nodeList.add(node);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		
		return nodeList;
 	}
	
	// �������
	public List<TreeNode> inorderTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		List<TreeNode> nodeList = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode p = root;
		while (p != null || !stack.isEmpty()) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				p = stack.pop();
				nodeList.add(p);
				p = p.right; 
			}
		}
		
		return nodeList;
	}
	
	// ��α���
	public List<TreeNode> levelTraversal(TreeNode root) {
		if (root == null) {
			return null;
		}
		List<TreeNode> nodeList = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			nodeList.add(node);
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		
		return nodeList;
	}
	
	
	public class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int val;
		
		public TreeNode() {
		}
	}
}
