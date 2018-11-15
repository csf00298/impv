//package com.guangbo.algorithm.sort;
//
///**
// * 归并排序
// *
// * @author gaoguangbo
// * @create 2018-04-25 下午6:42
// **/
//
//
//
///**
// * 递归总结：
// * 快速排序和归并排序都用到了递归思想
// * 相同点，都要不断的分割，分割到更小的粒度
// * 但两者稍有区别：
// * 快排：先计算然后一分为2，然后每个段落在重复
// * 归并：先无限分到最小，然后依次上层计算
// * 两者的不同，表现在代码上是处理逻辑的位置（相对于细分任务）
// */
//public class MergingSort {
//
//    /**
//     * 数组版本
//     *
//     * @param a
//     */
//    public void mergingSort(int[] a) {
//        sort(a, 0, a.length - 1);
//    }
//
//    public void sort(int[] a) {
//        this.sort(a, 0, a.length - 1);
//    }
//
//    private void sort(int[] a, int low, int high) {
//        if (low < high) {
//            int center = (low + high) >> 1;
//            sort(a, low, center);
//            sort(a, center + 1, high);
//            merge(a, low, center, high);
//        }
//    }
//
//    private void merge(int[] a, int low, int center, int high) {
//        int[] tempArr = new int[high - low + 1];
//        int tempIndex = 0;
//        int low2 = center + 1;
//        int tempLow = low;
//        //从两个数组中取值，每取连个数组中最小的（两个数组的头，哪个小，取哪个）
//        while (low <= center && low2 <= high) {
//            if (a[low] <= a[low2]) {
//                tempArr[tempIndex++] = a[low++];
//            } else {
//                tempArr[tempIndex++] = a[low2++];
//            }
//        }
//        //总会有一个取光了，另一个剩余
//        while (low <= center) {
//            tempArr[tempIndex++] = a[low++];
//        }
//
//        while (low2 <= high) {
//            tempArr[tempIndex++] = a[low2++];
//        }
//        //此时tempArr为排好序的数组
//        //将排好序的数组复制回去
//        tempIndex = 0;
//        while (tempLow <= high) {
//            a[tempLow++] = tempArr[tempIndex++];
//        }
//    }
//
//    /**
//     * 链表版本
//     *
//     * @param head
//     */
//
//    public ListNode sort(ListNode head) {
//        if (head == null) {
//            return head;
//        }
//        ListNode firstNode = new ListNode(-1);
//        firstNode.next = head;
//        sortList(firstNode);
//        return firstNode.next;
//    }
//
//    private void sortList(ListNode head) {
//        ListNode oneStep = head.next;
//        ListNode twoStep = head.next;
//        while (twoStep.next != null && twoStep.next.next != null) {
//            oneStep = oneStep.next;
//            twoStep = twoStep.next.next;
//        }
//        while (twoStep.next != null) {
//            twoStep = twoStep.next;
//        }
//        if (oneStep != twoStep) {
//            //断链
//            ListNode onStepNext = oneStep.next;
//            oneStep.next = null;
//            sortList(head);
//            //后半部分不需要断链
//            ListNode rightNode = new ListNode(-1);
//            rightNode.next = onStepNext;
//            sortList(rightNode);
//            mergingSort(head, rightNode);
//        }
//    }
//
//
//    public void mergingSort(ListNode head, ListNode head2) {
//        ListNode newHead = new ListNode(0);
//        ListNode newHeadCopy = newHead;
//
//        ListNode low1 = head.next;
//        ListNode low2 = head2.next;
//
//        while (low1 != null && low2 != null) {
//            if (low1.val <= low2.val) {
//                newHead.next = low1;
//                newHead = newHead.next;
//                low1 = low1.next;
//            } else {
//                newHead.next = low2;
//                newHead = newHead.next;
//                low2 = low2.next;
//            }
//        }
//        while (low1 != null) {
//            newHead.next = low1;
//            newHead = newHead.next;
//            low1 = low1.next;
//        }
//        while (low2 != null) {
//            newHead.next = low2;
//            newHead = newHead.next;
//            low2 = low2.next;
//        }
//        head.next = newHeadCopy.next;
//    }
//
//
//    public static void main(String[] args) throws InterruptedException {
//        Sort.test(new MergingSort());
//        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
//        MergingSort mergingSort = new MergingSort();
//        mergingSort.mergingSort(a);
//        for (int i : a) {
//            System.out.println(i);
//        }
//        System.out.println("------------------------");
//        ListNode head = new ListNode(6);
//        System.out.println(head.val);
//        ListNode first = head;
//
//        for (int i = 0;i<1;i++) {
//            first.next = new ListNode(RandomUtil.randomInt(3+20));
//            first = first.next;
//            System.out.println(first.val);
//        }
//        head = mergingSort.sort(head);
//        Thread.sleep(3000);
//        System.out.println("------------------------");
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
//    }
//}
//
//class ListNode{
//
//}