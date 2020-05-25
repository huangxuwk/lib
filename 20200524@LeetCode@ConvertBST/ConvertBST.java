package com.dl.test5;

import com.dl.util.TreeNode;

public class ConvertBST {
    int num = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            root.val = root.val + num;
            num = root.val;
            convertBST(root.left);
        }
        return root;
    }
}
