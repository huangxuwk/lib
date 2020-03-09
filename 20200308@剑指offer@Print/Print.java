package com.dl.test4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.dl.util.TreeNode;

public class Print {

	public Print() {
	}
	
    public ArrayList<ArrayList<Integer> > print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> llist = new ArrayList<>();
        if (pRoot == null) {
            return llist;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        int size = 1;
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        llist.add(list);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
                count++;
            } 
            if (node.right != null) {
                queue.offer(node.right);
                count++;
            }                
            if (--size == 0 && count != 0) {
                size = count;
                count = 0;
                list = new ArrayList<>();
                llist.add(list);
            }
        }
        
        for (int i = 1; i < llist.size(); i += 2) {
            list = llist.get(i);
            Collections.reverse(list);
        }
        return llist;
    }
}
