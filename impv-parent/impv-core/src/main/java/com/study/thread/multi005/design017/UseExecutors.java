package com.study.thread.multi005.design017;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: Created by Admin on 2016/11/25.
 * http://blog.csdn.net/nk_tf/article/details/51959276
 * http://zy116494718.iteye.com/blog/1704344
 */
public class UseExecutors {
    public static void main(String[] args) {
        ExecutorService service1 = Executors.newCachedThreadPool();
        ExecutorService service2 = Executors.newFixedThreadPool(5);
        ExecutorService service3 = Executors.newScheduledThreadPool(5);
        ExecutorService service4 = Executors.newSingleThreadExecutor();

        service1.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Do Task");
            }
        });

        service1.shutdown();
    }
}
