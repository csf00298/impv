package com.study.thread.multi001.sync006;

/**
 * @Description: 锁对象的改变问题
 * 引用常量字符串作为锁对象 修改常量的引用会影响 线程锁
 */
public class ChangeLock {
    private String lock = "lock";
    public void changeLock(){
        synchronized (lock){
            System.out.println(Thread.currentThread().getName()+" begin");
            lock = "new Lock"; //修改常量的引用 修改锁对象
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ChangeLock demo = new ChangeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.changeLock();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.changeLock();
            }
        },"t2");

        t1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
