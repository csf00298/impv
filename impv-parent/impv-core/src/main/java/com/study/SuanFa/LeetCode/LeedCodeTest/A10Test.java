package com.study.SuanFa.LeetCode.LeedCodeTest;

import java.util.HashMap;
import java.util.Map;

public class A10Test {
    public static void main(String[] args) {
        int [] source = {2,45,32,7};
        int[] ints = twoSum(source, 9);
        System.out.println(ints);
    }

    public static int[] twoSum(int[] source,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            Integer integer = map.get(target - source[i]);
            if(integer == null )
                map.put(source[i],i);
            else
                return new int[]{source[i],i};
        }
        return null;
    }
}
