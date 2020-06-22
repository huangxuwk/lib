package com.dl.text5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.dl.util.Node;

public class Postorder {

    public List<Integer> postorder(Node root) {
        List<Integer> res_pre = new ArrayList<>();
        if(root == null)
            return res_pre;
        Stack<Node> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            Node n = s.pop();
            res_pre.add(n.val);
            for(Node node : n.children)
                s.push(node);
        }
        Collections.reverse(res_pre);
        return res_pre;
    }
	
}
