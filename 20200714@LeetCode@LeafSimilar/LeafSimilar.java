package src.com.jd.leetcode.textB;

import src.com.jd.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class LeafSimilar {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = getLeafList(root1);
        List<Integer> list2 = getLeafList(root2);
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    // 变相的迭代中序遍历
    private List<Integer> getLeafList(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if (p.right == null && p.left == null) {
                    list.add(p.val);
                }
                p = p.right;
            }
        }
        return list;
    }

}
