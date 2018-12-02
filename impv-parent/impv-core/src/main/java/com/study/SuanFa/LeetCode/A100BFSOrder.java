package com.study.SuanFa.LeetCode;

import com.study.SuanFa.LeetCode.POJO.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 树的层次遍历
 */
public class A100BFSOrder {

    public static void main(String[] args) {
        TreeNode b1 = new TreeNode('a');
        TreeNode b2 = new TreeNode('b');
        TreeNode b3 = new TreeNode('c');
        TreeNode b4 = new TreeNode('d');
        TreeNode b5 = new TreeNode('e');
        TreeNode b6 = new TreeNode('f');
        TreeNode b7 = new TreeNode('g');

        /**
         *      a
         *    /   \
         *   b     c
         *  / \   / \
         * d   e f   g
         */
        b1.left = b2;
        b1.right = b3;
        b2.left = b4;
        b2.right = b5;
        b3.left = b6;
        b3.right = b7;

        BFSOrder(b1);
        System.out.println();
    }

    public static void BFSOrder(TreeNode root){
        Queue<TreeNode> treeNodes = new ArrayDeque<>();
        treeNodes.offer(root);
        while (!treeNodes.isEmpty()){
            TreeNode node = treeNodes.poll();
            System.out.println((char) node.val);
            if(node.left != null)
                treeNodes.offer(node.left);
            if(node.right != null)
                treeNodes.offer(node.right);
        }
    }
}
