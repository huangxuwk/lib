package com.dl.test4;

import java.util.LinkedList;
import java.util.Queue;

import com.dl.util.TreeNode;

public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 遍历得到每个结点，对每个结点进行交换
            TreeNode node = queue.poll();
            // 交换左右孩子
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
