package com.study.SuanFa.sort.MySort;

import java.util.Arrays;

/**
 * 快速排序算法
 * 1、选取数组其中一个作为基准值
 * 2、移动高位 与 基准值对比 如果小于基准 将该高位移动至基准值位
 * 3、移动低位 与 基准值对比 如果大于基准 将该低位移动至上一步的高位
 * 4、循环2-3
 * 5、直到高位与低位重合 一次循环结束
 * 6、此时基准值的左边均小于基准 右边均大于基准 对两边的数组分别 递归 重复1-5
 */
public class FastSort {

    public static void main(String[] args) {
        int[] arr = {3, 1, 7, 9, 4, 6, 8, 2};
        sort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(e -> System.out.print(e));
    }

    public static void sort(int[] arr, int l, int h) {
        if (l >= h) return;
        int low  = l;
        int high = h;
        int base = arr[low];
        while (low < high) {
            for (; ; high--) {
                if (high <= low) break;
                if (base > arr[high]) {
                    arr[low] = arr[high];
                    break;
                }
            }
            for (; ; low++) {
                if (high <= low) break;
                if (base < arr[low]) {
                    arr[high] = arr[low];
                    break;
                }
            }
        }
        if (low == high)
            arr[low] = base;

        sort(arr, l, low - 1);
        sort(arr, low + 1, h);

    }
}
