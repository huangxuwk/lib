package src.com.jd.leetcode.textF;

import src.com.jd.leetcode.util.TreeNode;

public class SumRootToLeaf {
    public static final int modle = (int)Math.pow(10, 9) + 7;

    private int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        sumNode(root, 0);
        return sum;
    }

    public void sumNode(TreeNode root, int num) {
        if (root == null) {
            return;
        }
        num = (num << 1) + root.val;
        if (root.left == null && root.right == null) {
            sum = (num + sum) % modle;
            return;
        }
        sumNode(root.left, num);
        sumNode(root.right, num);
    }

}
