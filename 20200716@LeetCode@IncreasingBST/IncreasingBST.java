package src.com.jd.leetcode.textC;

import src.com.jd.leetcode.util.TreeNode;

import java.util.Stack;

public class IncreasingBST {

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        TreeNode pre = null;
        TreeNode head = null;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if (pre == null) {
                    head = p;
                } else {
                    pre.right = p;
                    pre.left = null;
                }
                pre = p;
                p = p.right;
            }
        }
        return head;
    }

}
