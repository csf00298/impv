package com.study.thread.multi005.design019;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: Created by Admin on 2016/11/29.
 * Semaphore: 一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。
 * 每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，
 * 并采取相应的行动。拿到信号量的线程可以进入代码，否则就等待。通过acquire()和release()获取和释放访问许可
 */
public class UseSemaphore {
    public static void main(String[] args) {
        // 线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        // 只能3个线程同时访问
        final Semaphore  semaphore = new Semaphore(3);

        // 模拟20个客户端访问
        for (int i = 0; i < 20; i++) {
            final int count = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取许可
                        semaphore.acquire();
                        System.out.println("Accessing: " + count);
                        //模拟实际业务逻辑
                        Thread.sleep((long) (Math.random() * 10000));
                        // 访问完后，释放
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 退出线程池
        executor.shutdown();

    }
}
