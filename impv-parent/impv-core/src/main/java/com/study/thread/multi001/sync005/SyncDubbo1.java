package com.study.thread.multi001.sync005;

/**
 * @Description: synchronized的重入
 */
public class SyncDubbo1 {
    public synchronized  void mothod1(){
        System.out.println("method1..."+Thread.currentThread().getName());;
        method2();
    }

    public synchronized void method2(){
        System.out.println("method2..."+Thread.currentThread().getName());
        method3();
    }
    public synchronized void method3(){
        System.out.println("mehtod3..."+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final SyncDubbo1 demo = new SyncDubbo1();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.mothod1();
            }
        },"testThread");
        t.start();
    }
}
