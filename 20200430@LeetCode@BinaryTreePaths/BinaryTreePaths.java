package com.dl.test8;

import java.util.ArrayList;
import java.util.List;

import com.dl.util.TreeNode;

public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        return binary(root, "", new ArrayList<>());
    }

    private List<String> binary(TreeNode node, String path, List<String> list) {
        if (node == null) {
            return list;
        }
        path += String.valueOf(node.val);
        if (node.left == null && node.right == null) {
            list.add(path);
        } else {
            path += "->";
            binary(node.left, path, list);
            binary(node.right, path, list);
        }
        return list;
    }
	
}
