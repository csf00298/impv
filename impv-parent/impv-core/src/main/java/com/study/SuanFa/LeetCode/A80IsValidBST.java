package com.study.SuanFa.LeetCode;

import com.study.SuanFa.LeetCode.POJO.TreeNode;

/**
 * Validate Binary Search Tree（验证二叉搜索树）
 * 二叉搜索树：
 *  若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 *  若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
 *
 *采用递归的方法求解思路比较简单。对于一个节点，其值一定大于他的左子树的所有值，小于他的右子树的所有值，可以发现，该节点的值可以作为左子树的最大值，右子树的最小值。
 *
 * 利用该性质编写代码，在每次遍历中，用节点的值替代左子树的最大值和右子树的最小值。对于根节点，最小值和最大值分别为LONG_MIN和LONG_MAX
 */
public class A80IsValidBST {

    public boolean isValidBST(TreeNode root , Integer min, Integer max){
        if (root == null )return true;
        if(root.val<=min || root.val >=max) return false;
        return isValidBST(root.left,min,root.val) && isValidBST(root.right,root.val,max);
    }
}
