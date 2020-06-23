package com.dl.text5;

import com.dl.util.TreeNode;

public class Tree2str {

    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        treeStr(t, sb);
        return sb.toString();
    }

    private void treeStr(TreeNode t, StringBuilder sb) {
        if (t != null) {
            sb.append(t.val);
            // ֻҪ���������������������Ƿ�Ϊ�ն�Ҫ�����ţ���������Ϊ��ʱ����ʡ��
            if (t.left != null || t.right != null) {
                sb.append("(");
                treeStr(t.left, sb);
                sb.append(")");
                if (t.right != null) {
                    sb.append("(");
                    treeStr(t.right, sb);
                    sb.append(")");                
                }
            }        
        }
    }
	
}
