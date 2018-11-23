package com.study.SuanFa.LeetCode;

import com.study.SuanFa.msSuanfa.POJO.ListNode;

/**
 * 题目:
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * 翻译:
 * 合并2个已经排序的链表，并且返回一个新的链表。这个新的链表应该由前面提到的2个链表的节点所组成。
 * 分析：
 * 注意头节点的处理，和链表结束（next为null）的处理。以下代码新增了一个头指针，来把头节点的处理和普通节点的处理统一了。
 */
public class A30MergeTwoLinkedList {

    public ListNode merge(ListNode n1, ListNode n2) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        while (true) {
            if (n1 == null && n2 == null) break;
            else if (n2 != null && (n1 == null || n2.data < n1.data)) {
                current.next = n2;
                n2 = n2.next;
            } else {
                current.next = n1;
                n1 = n1.next;
            }
            current = current.next;
        }
        return head.next;
    }
}
