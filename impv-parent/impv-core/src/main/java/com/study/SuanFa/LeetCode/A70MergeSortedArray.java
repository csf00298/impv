package com.study.SuanFa.LeetCode;

/**
 *  Merge Sorted Array
 * 给定2个排序好的整数数组nums1和nums2，把nums2合并到nums1中成为1个排序的数组。
 * 提示：你可以假定nums1有足够的空间（大小>=m+n）来容纳来自nums2的额外的元素。nums1和nums2的元素的个数各自被初始化为m和n。
 * <p>
 * 分析：
 * 1. 如果nums1从前往后遍历的话，nums2中的元素需要插入nums1，这个时候每插入一次，就会需要将nums1的元素往后移动（或者需要申请额外的存储空间）。但是我们反过来想，由于合并后的数组长度是确定的，我们可以从最大的数开始写入，这个时候由于nums1的后面部分的空间是未使用的，刚好可以直接覆写。
 * 2. 需要考虑比较特殊的情况，就是数组可能为空。2个数组都为空自然进不去循环。但是其中一个为空，就要考虑数组可能发生越界的情况了。
 */
public class A70MergeSortedArray {
    public int[] merge(int[] arr1, int[] arr2) {
        int m = arr1.length - 1;
        int n = arr2.length - 1;
        int p = m;
        int q = n;
        for (int i = m + n - 1; i >= 0; i--) {
            if (arr1[p] < arr2[q]) {
                arr1[i] = arr2[q];
                q--;
            } else {
                arr1[i] = arr1[p];
                p--;
            }
        }
        return arr1;
    }
}
