package com.dl.text5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.dl.util.Node;

public class Preorder {

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            list.add(n.val);
            for (int i = n.children.size() - 1; i >= 0; i--) {
                stack.push(n.children.get(i));
            }
        }
        return list;
    }	
	
}
