package com.study.SuanFa.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断空串是回文对称
 */
public class A90ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("abcdccdcBa"));
    }

    public static boolean isPalindrome(String str) {
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if ((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= '0' && chars[i] <= '9')){
                list.add(chars[i]);
            }
            if(chars[i]>='A'+32 || chars[i]<='Z'+32){
                list.add(chars[i]);
            }
        }

        for(int i=0 ; i<list.size(); i++){
            if(list.get(i) == list.get(list.size()-1-i)){
                System.out.println(list.get(i)+"-"+list.get(list.size()-1-i));
            }
            else
                return false;
        }
        return true;
    }
}
