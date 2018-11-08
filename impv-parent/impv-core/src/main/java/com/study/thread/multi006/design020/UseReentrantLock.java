package com.study.thread.multi006.design020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: Created by Admin on 2016/12/1.
 */
public class UseReentrantLock {
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void method1() throws InterruptedException {
        try {
            reentrantLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method1..");
            Thread.sleep(1000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method1..");
            Thread.sleep(1000);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void method2()throws InterruptedException{
        try {
            reentrantLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method2..");
            Thread.sleep(2000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method2..");
            Thread.sleep(1000);
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseReentrantLock ur = new UseReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ur.method1();
                    ur.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        t1.start();
    }
}
