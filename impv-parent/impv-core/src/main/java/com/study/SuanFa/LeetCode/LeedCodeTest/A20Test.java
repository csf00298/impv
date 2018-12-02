package com.study.SuanFa.LeetCode.LeedCodeTest;

import java.util.ArrayList;
import java.util.List;

public class A20Test {

    public static void main(String[] args) {
        System.out.println(isBlance("{}][[]()"));
    }

    public static boolean isBlance(String targ){
        char[] chars = targ.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '('||chars[i] == '{'||chars[i] == '['){
                list.add(chars[i]);
            }else{
                if(list.size() == 0) return false;
                char head = list.get(list.size()-1);
                if(head == ')' && chars[i] == '(')return false;
                if(head == ']' && chars[i] == '[')return false;
                if(head == '}' && chars[i] == '{')return false;
                list.remove(list.size()-1);
            }
        }
        if(list.size() == 0) return true;
        else return false;
    }

}
