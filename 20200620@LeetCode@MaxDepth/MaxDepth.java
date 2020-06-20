package com.dl.text4;

import com.dl.util.Node;

public class MaxDepth {
    private int depth;

    public int maxDepth(Node root) {
       depth(root, 0);
       return depth; 
    }

    private void depth(Node root, int high) {
        if (root == null) {
            return;
        }
        depth = Math.max(depth, high + 1);
        for (Node node : root.children) {
            depth(node, high + 1);
        }
    }
}
