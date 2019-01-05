package com.study.SuanFa.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * Given a string containing just the characters , determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order, “()” and “()[]{}” are all valid but “(]” and “([)]” are not.
 * <p>
 * 翻译：
 * 给定一个字符串，只包含’(‘, ‘)’, ‘{‘, ‘}’, ‘[’ 和’]’这些字符，检查它是否是“有效”的。
 * 括号必须以正确的顺序关闭，例如”()” 和”()[]{}”都是有效的，”(]” 和”([)]”是无效的。
 * <p>
 * 分析：
 * 本题考查的是栈结构，具有后进先出的特性。有效包含2个方面，第一个是如果是关闭的括号，前一位一定要刚好有一个开启的括号；第二个是最终结果，需要把所有开启的括号都抵消完。一个比较容易出错的地方是，遇到关闭括号时，要先判断栈是否已经空了。
 */
public class A20ValidParentheses {

    public static void main(String[] args) {
        String str = "{[]()}";
        System.out.println(isBlance(str));
    }

    public static boolean isBlance(String strTarg) {
        char[] chars = strTarg.toCharArray();
        List<Character> list = new ArrayList<>();
        for (Character c : chars) {
            if (c == '(' || c == '[' || c == '{')
                list.add(c);
            else {
                if (list.size() == 0) return false;
                char last=list.get(list.size()-1);
                if (c == ')' && last != '(') return false;
                if (c == ']' && last != '[') return false;
                if (c == '}' && last != '{') return false;
                list.remove(list.size() - 1);
            }
        }
        if (list.size() != 0) return false;
        return true;
    }
}
