package com.dl.text6;

import java.util.HashSet;
import java.util.Set;

import com.dl.util.TreeNode;

public class FindTarget {
    private Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
