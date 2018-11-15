package com.study.SuanFa.msSuanfa;

/**
 * Search in Rotated Sorted Array 旋转数组查找 TODO
 * (i.e., 1 2 4 5 6 7 8 9 might become 7 8 9 1 2 3 4 5 6 )
 */
public class sf02 {

    public static void main(String[] args) {
        int arr[] = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        System.out.println(getTargNo(arr, 5));
    }

    public static int getTargNo(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (arr[low] <= arr[mid]) {
                if (target > arr[mid] && target < arr[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            } else {
                if (target > arr[low] && target < arr[mid])
                    low = mid - 1;
                else
                    high = mid + 1;
            }
        }
        return arr[low] == target ? target : -1;
    }
}
