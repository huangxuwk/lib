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
            // 只要存在子树，无论左子树是否为空都要加括号，而右子树为空时可以省略
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
