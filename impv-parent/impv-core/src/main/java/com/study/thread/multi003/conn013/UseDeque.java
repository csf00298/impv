package com.study.thread.multi003.conn013;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description: Created by Admin on 2016/11/11.
 */
public class UseDeque {
    public static void main(String[] args) {
        //初始化有界队列
        LinkedBlockingDeque<String> dq = new LinkedBlockingDeque<>(7);//6
        dq.addFirst("a");
        dq.addFirst("b");
        dq.addFirst("c");
        dq.addFirst("d");
        dq.addLast("f");
        dq.addLast("g");
        dq.addLast("h");

        Object[] array = dq.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
