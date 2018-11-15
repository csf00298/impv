package com.study.SuanFa.msSuanfa;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有序阻塞队列
 */
public class MyLinkedBlockQueue {

    private Lock lock = new ReentrantLock();
    private Condition fullCdt = lock.newCondition();
    private Condition emptyCdt = lock.newCondition();
    private LinkedList<String> queue = new LinkedList<>();
    private int queSize = 0;

    public void add(String str) throws InterruptedException {
        try {
            lock.tryLock();
            while (queSize == queue.size()) {
                fullCdt.await();
            }
            queue.add(str);
            queSize++;
            fullCdt.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() == 0) {
                emptyCdt.await();
            }
            queue.pop();
            queSize -- ;
            emptyCdt.signal();
        } finally {
            lock.unlock();
        }
    }
}
