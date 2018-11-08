package com.study.thread.eventBus.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {
    public static void main(String[] args) throws InterruptedException {
        MyThread mt1 = new MyThread();
        MyThread mt2 = new MyThread();

        Thread t1 = new Thread(mt1);
        Thread t2 = new Thread(mt2);
        t1.start();
        t2.start();
        Thread.sleep(500);
        System.out.println(MyThread.ai.get());
    }
}

class MyThread implements Runnable {
    //    static  int i = 0;
     public static AtomicInteger ai=new AtomicInteger(0);


    public void run() {
        for (int m = 0; m < 1000000; m++) {
            ai.getAndIncrement();
        }
    }
};