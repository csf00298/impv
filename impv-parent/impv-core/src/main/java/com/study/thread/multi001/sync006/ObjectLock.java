package com.study.thread.multi001.sync006;

/**
 * @Description: 使用synchronized代码块加锁,比较灵活
 */
public class ObjectLock {
    public void method1(){
        synchronized (this){ //对象锁
            System.out.println("do method1..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2(){
        synchronized (ObjectLock.class){ //类锁
            System.out.println("do method3..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method3(){
        Object obj = new Object();
        synchronized (obj){ //任何对象锁
            System.out.println("do method3..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        final ObjectLock demo = new ObjectLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.method1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.method2();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.method3();
            }
        }).start();
    }
}
