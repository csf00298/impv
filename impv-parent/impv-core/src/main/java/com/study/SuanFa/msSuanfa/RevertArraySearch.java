package com.study.SuanFa.msSuanfa;

/**
 * 反转数组查找
 * 原数组：  123456789
 * 反转数组：789123456
 */
public class RevertArraySearch {
    public static void main(String[] args) {
        int[] a = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        System.out.println(revertSearch(a, 6));
    }

    public static int revertSearch(int[] arr, int targ) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (targ == arr[mid]) return mid;
            if (arr[low] < arr[mid]) { // 左边正序
                if (targ >= arr[low] && targ <= arr[mid]) {
                    high = mid - 1;
                } else
                    low = mid + 1;
            } else {
                if (targ >= arr[mid] && targ <= arr[high]) {
                    low = mid + 1;
                } else
                    high = mid - 1;
            }
        }
        return -1;
    }
}
