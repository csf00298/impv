package com.study.thread.multi002.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Created by Admin on 2016/11/10.
 */
public class ListAdd2 {
    //线程间通信  变量共享
    private volatile static List<Integer> list = new ArrayList<Integer>();

    private void add(int str) {
        list.add(str);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd2 demo = new ListAdd2();
        final Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            demo.add(i);
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素.." + i);
                            Thread.sleep(500);
                            if (demo.size() == 5) {
                                System.out.println("已经发出通知..");
                                lock.notify();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        if (demo.size() != 5) {
                            lock.wait(); //会释放线程资源
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                    throw new RuntimeException();
                }
            }
        }, "t2");
        t2.start();
        t1.start();
    }
}
