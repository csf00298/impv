package com.study.SuanFa.msSuanfa;


import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore可以用于做流量控制，特别公用资源有限的应用场景，比如数据库连接。
 * 假如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程并发的读取，
 * 但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，这时我们必须控制只有十个线程
 * 同时获取数据库连接保存数据，否则会报错无法获取数据库连接。这个时候，我们就可以使用Semaphore来做流控
 */
public class SemaphoreCase {
    public static void main(String[] args) {
        Semaphore count = new Semaphore(5);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                try {
                    count.acquire();
                    System.out.println("资源获取 start");
                    Thread.sleep(RandomUtils.nextInt(60,1000));
                    System.out.println(Thread.currentThread().getName());
                    count.release();
                    System.out.println("资源释放 over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}
