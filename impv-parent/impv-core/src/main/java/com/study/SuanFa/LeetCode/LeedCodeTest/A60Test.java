package com.study.SuanFa.LeetCode.LeedCodeTest;


import com.study.SuanFa.LeetCode.POJO.TreeNode;

public class A60Test {

    public boolean isBlance(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(treeDep(root.right) - treeDep(root.left)) > 1) {
            return false;
        } else
            return isBlance(root.left) && isBlance(root.right);
    }

    public int treeDep(TreeNode root) {
        if (root == null) return 0;
        else return Math.max(treeDep(root.left), treeDep(root.right)) + 1;
    }

}
