package com.study.thread.TThreadLocal;

import org.junit.Test;


public class TThreadLocal {

    @Test
    public void testThreadLocal() {

        ThreadLocal tl = new ThreadLocal();
        Task task = new Task(tl);
        Task task1 = new Task(tl);
        Task task2 = new Task(tl);

        new Thread(task).start();
        new Thread(task1).start();
        new Thread(task2).start();
    }

    class Task implements Runnable {

        private ThreadLocal tl;

        public Task(ThreadLocal tl) {
            this.tl = tl;
        }

        @Override
        public void run() {
            while (true) {
                int count = tl.get() == null ? 0 : (int) tl.get();
                int count2 = tl.get() == null ? 0 : (int) tl.get();
                System.out.println(Thread.currentThread().getName() + " count1= " + count++);
                System.out.println(Thread.currentThread().getName() + " count2= " + count2++);
                tl.set(count);
                tl.set(count2);
            }
        }
    }
}
