package com.study.SuanFa.LeetCode;

/**
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 */
public class A40impStrStr {
    public static void main(String[] args) {
        System.out.println(impStr("helllo", "lolo"));
    }

    public static Integer impStr(String source, String target) {
        if (source.contains(target)) {
            char[] sourceC = source.toCharArray();
            char[] targetC = target.toCharArray();
            for (int i = 0; i < sourceC.length - targetC.length +1; i++) {
                String tmp = source.substring(i, i + targetC.length); //指针顺移
                if (tmp.equals(target))
                    return i ;
            }
            return -1;
        } else
            return -1;
    }
}
