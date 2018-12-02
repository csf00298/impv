package com.study.SuanFa.LeetCode.LeedCodeTest;

import com.study.SuanFa.msSuanfa.POJO.ListNode;

public class A30Test {

    public ListNode mergeTwo(ListNode n1, ListNode n2){
        ListNode head = new ListNode(0);
        ListNode current = head;
        while (true){
            if(n1 == null && n2 == null) break;
            else if(n2!=null && (n1 == null || n2.data<n1.data)){
                current.next = n2;
                n2 = n2.next;
            }else{
                current.next = n1;
                n1 = n1.next;
            }
            current = current.next;
        }
        return head.next;
    }
}
