package com.study.thread.multi006.design021;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁  读读共享、写写互斥
 * 都是读锁不会被阻塞；
 * 读写同时会被阻塞 需要一方先释放锁
 */
public class UseReentrantReadWriteLock {
    private ReentrantReadWriteLock rrwL = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = rrwL.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = rrwL.writeLock();

    public void read(){
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public void write(){
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseReentrantReadWriteLock userrwL = new UseReentrantReadWriteLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                userrwL.read();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                userrwL.read();
            }
        },"t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                userrwL.write();
            }
        },"t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                userrwL.write();
            }
        },"t4");

        t1.start();
        t2.start();
        t3.start();
//        t4.start();
    }
}
