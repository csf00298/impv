package com.study.thread.multi005.design019;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * t1会阻塞，在 t2、t3执行完成之后继续执行t1。
 * countDown方法，当前线程调用此方法，则计数减一
 * awaint方法，调用此方法会一直阻塞当前线程，直到计时器的值为0
 */
public class UseCountDownLatch {
    public static void main(String[] args) {
        final CountDownLatch downLatch = new CountDownLatch(2); //计数2个 在执行了两次countDown之后才会执行t1

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("进入线程t1" + "等待其他线程处理完成...");
                    downLatch.await(); //线程进入阻塞 等待downLatch中的计数减到0为止
                    System.out.println("t1线程继续执行其他操作...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2线程进行-某些初始化操作...");
                    Thread.sleep(3000);
                    System.out.println("t2线程初始化完毕，通知t1线程继续...");
                    downLatch.countDown(); //线程执行 downLatch计数减一
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3线程进行-某些初始化操作...");
                    Thread.sleep(4000);
                    System.out.println("t3线程初始化完毕，通知t1线程继续...");
                    downLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
