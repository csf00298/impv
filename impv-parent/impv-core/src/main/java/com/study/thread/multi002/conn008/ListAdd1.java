package com.study.thread.multi002.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Created by Admin on 2016/11/10.
 */
public class ListAdd1 {
    //线程间通信  变量共享
    private volatile static List<Integer> list = new ArrayList<Integer>();

    private void add(int str) {
        list.add(str);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd1 demo = new ListAdd1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        demo.add(i);
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素.." + i);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(demo.size() == 5){
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
