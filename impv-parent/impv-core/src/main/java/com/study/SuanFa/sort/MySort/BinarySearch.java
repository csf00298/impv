package com.study.SuanFa.sort.MySort;

/**
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9,};
        System.out.println(sort(arr, 8, 0, arr.length - 1));
    }

    public static int sort(int arr[], int target, int low, int high) {
        int mid = (low + high) >> 1;
        if (target == arr[mid]) return mid + 1;
        if (target > arr[mid]) return sort(arr, target, mid + 1, high);
        else return sort(arr, target, low, mid - 1);
    }

}
