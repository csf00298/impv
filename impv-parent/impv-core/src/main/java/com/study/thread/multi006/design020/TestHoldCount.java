package com.study.thread.multi006.design020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: lock.getHoldCount()方法：只能在当前调用线程内部使用，不能再其他线程中使用
 * 那么我可以在m1方法里去调用m2方法，同时m1方法和m2方法都持有lock锁定即可 测试结果holdCount数递增
 *
 * http://ifeve.com/reentrantlock-and-fairness/
 * https://my.oschina.net/noahxiao/blog/101558
 */
public class TestHoldCount {
    //重入锁
    private ReentrantLock lock = new ReentrantLock();

    public void method1() throws InterruptedException {
        try {
            lock.lock();
//            lock.lock();
            System.out.println("进入method1方法，holdCount数为：" + lock.getHoldCount());
            Thread.sleep(500);
            //调用m2方法
            method2();
        } finally {
            lock.unlock();
        }
    }

    public void method2() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("进入method2方法，holdCount数为：" + lock.getHoldCount());
            Thread.sleep(500);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestHoldCount th = new TestHoldCount();
        try {
            th.method1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
