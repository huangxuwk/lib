package com.dl.test6;

import com.dl.util.TreeNode;

public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(0, nums.length - 1, nums);
    }

    private TreeNode buildTree(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        //int p = left + (right - left) / 2;
        int p = (right + left) / 2;
        TreeNode root = new TreeNode(nums[p]);
        root.left = buildTree(left, p - 1, nums);
        root.right = buildTree(p + 1, right, nums);
        return root;
    }
    
}
