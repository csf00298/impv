package com.study.SuanFa.msSuanfa;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * https://blog.csdn.net/tanjie_123/article/details/53007674
 */
public class sf01 {

    public static void main(String[] args) {
        System.out.println(fun(2));
    }

    public static int fun(int n) {
        if (1 == n || 2 == n )
            return n;

        return fun(n - 1) + fun(n - 2);
    }
}
