package com.dl.text2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.dl.util.TreeNode;

public class FindMode {
    // �����������е�����
    private Set<Integer> set = new HashSet<>();
    // ��¼�������ֵ�Ƶ��
    private int max;
    // ��¼ÿһ�����ĳ���Ƶ�ʣ�����������������ӵ�list
    private int count = 1;
    // ǰ�����
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
        // root��������
        if (preNode == null || root.val != preNode.val) {
            count = 1;
        } else if (root.val == preNode.val) {
            count++;
        }
        if (count > max) {
            max = count;
            // ���ִ����������������Ҫ�����ǰ��
            set.clear();
            set.add(root.val);
        } else if (count == max) {
            set.add(root.val);
        }
        preNode = root;
        middleOrderErgodic(root.right);
    } 
}
