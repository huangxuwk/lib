package com.dl.textK;

import com.dl.util.TreeNode;

public class RangeSumBST {
    private int result;
    private boolean range;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        rangeSumBST(root.left, L, R);
        if (root.val == L) {
            range = true;
            result += root.val;
        } else if (root.val == R) {
            range = false;
            result += root.val;
            return result;
        } else {
            if (range) {
                result += root.val;
            }
        }
        rangeSumBST(root.right, L, R);
        return result;
    }
}
