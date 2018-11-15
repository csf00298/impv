package com.study.SuanFa.msSuanfa;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 控制多线程的执行，仅当值减为0时从阻塞状态变为就绪状态
 * 应用场景：多线程任务在同一时间由阻塞状态开始运行
 *
 * 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Runnable run = () -> {
                try {
                    begin.await();
                    Thread.sleep(RandomUtils.nextInt(0,1000));
                    System.out.println(Thread.currentThread().getName()+"--"+end.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    end.countDown();
                }
            };
            executorService.submit(run);
        }
        System.out.println("任务开始");
        begin.countDown();
        executorService.shutdown();
    }
}
