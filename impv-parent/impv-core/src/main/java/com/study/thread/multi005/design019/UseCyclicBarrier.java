package com.study.thread.multi005.design019;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (给定数目的线程都就绪等待后)。才执行
 * http://blog.csdn.net/shihuacai/article/details/8856407
 */
public class UseCyclicBarrier {
    static class DoTask implements Runnable{

        private CyclicBarrier barrier;
        private String name;

        public DoTask(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * (new Random()).nextInt(5));
                System.out.println(name + " 准备完毕，等待执行.");
                barrier.await(); // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(name + " 执行任务!!!");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);//在给定的线程数(3)都准备完毕之后，才开始执行任务

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new DoTask(cyclicBarrier,"thread1"));
        executor.execute(new DoTask(cyclicBarrier,"thread2"));
        executor.execute(new DoTask(cyclicBarrier,"thread3"));

        executor.shutdown();
    }
}
