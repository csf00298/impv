package com.study.SuanFa.LeetCode;

/**
 * 判断平衡二叉树
 * https://m.aliyun.com/jiaocheng/802424.html
 * 从根节点开始,求出根的左右子树的高度,如果根的左右子树的高度差大于1,返回FALSE,否则递归的判断根的左子树和右子树是否满足条件
 */
public class A60IsBlanceTree {
    public boolean isBlance(TreeNode root) {
        if (root == null) return true;
        else if (Math.abs(treeDepth(root.left) - treeDepth(root.right)) > 1)
            return false;
        else return isBlance(root.right) && isBlance(root.left);
    }

    public Integer treeDepth(TreeNode root) {
        if (root == null) return 0;
        else return Math.max(treeDepth(root.right), treeDepth(root.left)) + 1;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}