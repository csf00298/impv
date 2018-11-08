package com.study.thread.multi001.sync007;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: Created by Admin on 2016/11/9.
 */
public class AtomicUse {
    private AtomicInteger count = new AtomicInteger(0);

    //多个addAndGet在一个方法内是非原子性的，需要加synchronized进行修饰，保证4个addAndGet整体原子性
    /*synchronized*/
    private int multiAdd() { //如果方法是原子性的 每次输出都会是10的倍数
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4);
        return count.get();
    }

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        final AtomicUse demo = new AtomicUse();
        for (int i = 0; i < 100; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(demo.multiAdd());
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }
    }
}
