package com.study.myLock;

/**
 * 可重入锁
 */
public class ReLock {

    private boolean isLock = false;
    private Thread lockedBy;
    private int count = 0;

    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (isLock && currentThread != lockedBy) {
            wait();
        }
        isLock = true;
        count++;
        lockedBy = currentThread;
    }

    public synchronized void unLock() {
        if (lockedBy == Thread.currentThread()) {
            count--;
            if (count == 0) {
                notify();
                isLock = false;
            }

        }

    }
}
