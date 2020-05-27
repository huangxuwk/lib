package com.dl.test5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.dl.util.TreeNode;

public class LevelOrderBottom {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> temp = new ArrayList<>();
        if (root == null) {
            return temp;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> list = new ArrayList<>();
        int count = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (--count == 0) {
                temp.add(list);
                list = new ArrayList<>();
                count = queue.size();
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
        return result;
    }
	
}
