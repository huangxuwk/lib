package src.com.jd.leetcode.textE;

import src.com.jd.leetcode.util.TreeNode;

public class IsCousins {
    private int xparent;
    // 对应x的深度
    private int xdepth;
    private int yparent;
    // 对应y的深度
    private int ydepth;
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || x == y || root.val == x || root.val == y) {
            return false;
        }
        dfs(root, x, y, 0, -1);
        return xdepth == ydepth && xparent != yparent;
    }

    private void dfs(TreeNode root, int x, int y, int depth, int parent) {
        if (root == null) {
            return;
        }
        if (x == root.val) {
            xparent = parent;
            xdepth = depth;
        } else if (y == root.val) {
            yparent = parent;
            ydepth = depth;
        } else {
            dfs(root.left, x, y, depth + 1, root.val);
            dfs(root.right, x, y, depth + 1, root.val);
        }
    }
}
