package com.dl.text6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.dl.util.TreeNode;

public class AverageOfLevels {
	
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 1;
        int num = count;
        long sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node =  queue.poll();
            sum += node.val;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (--count == 0) {
                list.add(sum * 1.0 / num);
                num = count = queue.size();
                sum = 0;
            }
        }
        return list;
    }
	
}