package com.study.thread.multi001.sync003;

/**
 * @Description: 对象锁的同步和异步问题
 */
public class MyObject {
    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName()+" method1 --");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** synchronized */
    public void method2(){
        System.out.println(Thread.currentThread().getName()+" method2 --");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //使用同一个对象调用方法

        /**
         * 分析：
         * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
         * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
         */
        final MyObject t = new MyObject();
         Thread t1= new Thread(new Runnable() {
             @Override
             public void run() {
                 t.method1();
             }
         },"thread-1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.method2();
            }
        },"thread-2");

        t1.start();
        System.out.println("t2 while run");
        t2.start();
    }
}
