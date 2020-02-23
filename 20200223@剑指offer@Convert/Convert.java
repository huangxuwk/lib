package com.dl.test1;

import java.util.ArrayList;
import java.util.List;

import com.dl.test1.Mirror.TreeNode;

public class Convert {
    private List<TreeNode> nodeList = new ArrayList<>();
	
	public Convert() {
	}
	
    public TreeNode convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        argodic(pRootOfTree);
        int size = nodeList.size();
        if (size == 1) {
        	return nodeList.get(0);
        }

        TreeNode node = nodeList.get(0);
        node.left = null;
        node.right = nodeList.get(1);
        for (int i = 1; i < size - 1; i++) {
        	node = nodeList.get(i);
        	node.left = nodeList.get(i - 1);
        	node.right = nodeList.get(i+1);
		}
        node = nodeList.get(size - 1);
        node.left = nodeList.get(size - 2);
        node.right = null;
        
        return nodeList.get(0);
    }
    
    private void argodic(TreeNode node) {
        if (node == null) {
            return;
        }
        argodic(node.left);
        nodeList.add(node);
        argodic(node.right);
    }
}
