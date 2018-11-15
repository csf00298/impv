package com.study.myLock;

/**
 * 不可重入锁
 * 当前线程执行某个方法已经获取了该锁，那么在方法中尝试再次获取锁时，就会获取不到被阻塞。
 */
public class UnReLock {

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }


    public synchronized void unLock() {
        isLocked = false;
        notify();
    }
}
