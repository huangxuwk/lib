package com.dl.text2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.dl.util.TreeNode;

public class FindMode {
    // 用来保存所有的众数
    private Set<Integer> set = new HashSet<>();
    // 记录众数出现的频率
    private int max;
    // 记录每一个数的出现频率，到达众数个数就添加到list
    private int count = 1;
    // 前驱结点
    private TreeNode preNode;

    public int[] findMode(TreeNode root) {
        int[] re = null;
        if (root == null) {
            return new int[]{};
        }
        if (root.left == null && root.right == null) {
            return new int[]{root.val};
        }
        middleOrderErgodic(root);
        re = new int[set.size()];
        Iterator<Integer> it = set.iterator();
        int index = 0;
        while (it.hasNext()) {
            re[index++] = it.next();
        }
        return re;
    }

    private void middleOrderErgodic(TreeNode root) {
        if (root == null) {
            return;
        }
        middleOrderErgodic(root.left);
        // root是最左结点
        if (preNode == null || root.val != preNode.val) {
            count = 1;
        } else if (root.val == preNode.val) {
            count++;
        }
        if (count > max) {
            max = count;
            // 出现次数更多的众数，需要清空以前的
            set.clear();
            set.add(root.val);
        } else if (count == max) {
            set.add(root.val);
        }
        preNode = root;
        middleOrderErgodic(root.right);
    } 
}
