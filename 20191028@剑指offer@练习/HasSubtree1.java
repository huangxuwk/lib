package com.dl.test3;

/*
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 * (ps：我们约定空树不是任意一个树的子结构）
 * 子树的根节点为原树的根节点或左右孩子
 */
public class HasSubtree1 {

		public boolean HasSubtree(TreeNode root1, TreeNode root2) {
		        if (root1 == null || root2 == null) {
		            return false;
		        }
		        return judgeSubTree(root1, root2) ||
		               judgeSubTree(root1.left, root2) ||
		               judgeSubTree(root1.right, root2);
		    }
		 
		    private boolean judgeSubTree(TreeNode root1, TreeNode root2) {
		        if (root2 == null) {
		            return true;
		        }
		        if (root1 == null) {
		            return false;
		        }
		        if (root1.val != root2.val) {
		            return false;
		        }
		        return judgeSubTree(root1.left, root2.left) &&
		               judgeSubTree(root1.right, root2.right);
		    }
    
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }
    
    public static void main(String[] args) {
    	HasSubtree1 h = new HasSubtree1();
		TreeNode root1 = h.new TreeNode(1);
		root1.left = h.new TreeNode(2);
		root1.right = h.new TreeNode(3);
		root1.left.left = h.new TreeNode(4);
		root1.left.right = h.new TreeNode(5);
		
		TreeNode root2 = h.new TreeNode(1);
		root2.right = h.new TreeNode(3);
//		root2.right = h.new TreeNode(5);
		
		System.out.println(h.HasSubtree(root1, root2));
	}
}

/*
	public boolean HasSubtree(TreeNode root1,TreeNode root2) {
    	if (root1 == null || root2 == null) {
    		return false;
    	}
    	TreeNode temp = root1;;
    	while (temp.left != null) {
    		if (temp.val == root2.val) {
    			if (judge(temp, root2)) {
    				return true;
    			}
    		}
    		temp = temp.left;
    	}
    	temp = root1;
    	while (temp.right != null) {
    		if (temp.val == root2.val) {
    			if (judge(temp, root2)) {
    				return true;
    			}
    		}
    		temp = temp.right;
    	}
    	
		return false;
    }
    
    public boolean judge(TreeNode root1,TreeNode root2) {
    	if (root1.val != root2.val) {
    		return false;
    	}
    	if (root2.left == null && root2.right == null) {
    		return true;
    	}
    	if ((root1.left == null && root2.left != null)
    			|| (root1.right == null && root2.right != null)) {
    		return false;
    	}
    	if (root2.left != null) {
    		if (judge(root1.left, root2.left)) {
    			return true;
    		}
    	}
    	if (root2.right != null) {
    		if (judge(root1.right, root2.right)) {
    			return true;
    		}
    	}
    	
		return false;
    }
 */
