package com.study.SuanFa.sort.MySort;

import java.util.Arrays;

public class FastSortTest {
    public static void main(String[] args) {
        int[] arr = {8, 1, 5, 7, 9, 6, 3, 1, 2};
        sort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(e -> System.out.print(e));
    }

    public static void sort(int[] arr, int l, int h) {
        if (l > h) return;
        int low = l;
        int high = h;
        int base = arr[l];

        while (low < high) {
            for (; ; high--) {
                if (low >= high) break;
                if (base > arr[high]) {
                    arr[low] = arr[high];
                    break;
                }
            }

            for (; ; low++) {
                if (low >= high) break;
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
