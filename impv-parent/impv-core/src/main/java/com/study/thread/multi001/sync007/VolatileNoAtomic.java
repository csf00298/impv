package com.study.thread.multi001.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: volatile关键字不具备synchronized关键字的原子性（同步）
 */
public class VolatileNoAtomic extends Thread {
    //private static volatile int count;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void add() {
        for (int i = 0; i < 100; i++) {
            count.incrementAndGet();
        }
        System.out.println(count.get());
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] demoArray = new VolatileNoAtomic[10];
        for (int i = 0; i < 10; i++) {
            demoArray[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < 10; i++) {
            demoArray[i].start();
        }
    }
}
