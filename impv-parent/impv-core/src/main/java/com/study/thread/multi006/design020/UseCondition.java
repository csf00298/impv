package com.study.thread.multi006.design020;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* @Description: 对于所有的锁 都需要在finally中释放
 */
public class UseCondition {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void method1() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待状态..");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁..");
            condition.await();	// Object wait
            System.out.println("当前线程：" + Thread.currentThread().getName() + "继续执行...");
        } finally {
            lock.unlock();
        }
    }

    public void method2() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入..");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒..");
            condition.signal();		//Object notify
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseCondition uc = new UseCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    uc.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    uc.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");

        t1.start();
        t2.start();
    }
}

