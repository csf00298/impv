package com.study.thread.multi006.design020;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 多个Condition 只能唤醒本Condition加锁的线程
 */
public class UseManyCondition {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public void method1() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m1等待..");
            condition1.await();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m1继续..");
        } finally {
            lock.unlock();
        }
    }

    public void method2() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m2等待..");
            condition1.await();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m2继续..");
        } finally {
            lock.unlock();
        }
    }

    public void method3() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m3等待..");
            condition2.await();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m3继续..");
        } finally {
            lock.unlock();
        }
    }

    public void method4() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "唤醒 Condition1-ALL..");
            condition1.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void method5() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("当前线程：" +Thread.currentThread().getName() + "唤醒 Condition2..");
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final UseManyCondition umc = new UseManyCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {umc.method1();
                } catch (InterruptedException e) { e.printStackTrace();}
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {umc.method2();
                } catch (InterruptedException e) { e.printStackTrace();}
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {umc.method3();
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try { umc.method4();
                } catch (InterruptedException e) { e.printStackTrace();}
            }
        });
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try { umc.method5();
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        });

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(2000);
        t4.start();
        Thread.sleep(2000);
        t5.start();
    }
}
