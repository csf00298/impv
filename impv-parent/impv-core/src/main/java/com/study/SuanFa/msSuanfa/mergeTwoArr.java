package com.study.SuanFa.msSuanfa;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并两个有序数组
 */
public class mergeTwoArr {

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 3, 5, 7, 8, 10,122,144);
        List<Integer> l2 = Arrays.asList(2, 3, 4, 6, 18, 110,133);
        System.out.println(mergeArray(l1, l2));
    }

    public static List<Integer> mergeArray(List<Integer> arr1, List<Integer> arr2) {
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) < arr2.get(j))
                list.add(arr1.get(i++));
            else
                list.add(arr2.get(j++));
        }
        if (i < arr1.size()) list.add(arr1.get(i++));
        if (j < arr2.size()) list.add(arr2.get(j++));
        return list;
    }
}
