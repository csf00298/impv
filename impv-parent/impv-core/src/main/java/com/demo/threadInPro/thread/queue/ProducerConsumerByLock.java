package com.demo.threadInPro.thread.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/7/4.
 */
public class ProducerConsumerByLock {
    static Queue queue = new LinkedList();
    static Lock lock = new ReentrantLock();
    static Condition emptyCondition = lock.newCondition();
    static Condition fullCondition = lock.newCondition();

    public static void main(String[] args) {
        Producer p1 = new Producer("p1",queue,5);
        Producer p2 = new Producer("p2",queue,5);
        Consumer c1 = new Consumer("c1",queue,5);
        Consumer c2 = new Consumer("c2",queue,5);
        Consumer c3 = new Consumer("c3",queue,5);
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();
    }


    public static class Producer extends Thread {
        private Queue queue;
        private String name;
        private Integer maxSize;
        private Integer taskNo = 0;

        public Producer(String name, Queue<Integer> queue, int maxSize) {
            super(name);
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while(true){
                lock.lock();
                while(queue.size() == maxSize){
                    try {
                        System.out .println("Queue is full, Producer[" + name + "] thread waiting for " + "consumer to take something from queue.");
                        fullCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.offer(++ taskNo);
                System.out.println("[" + name + "] Producing value : +" + taskNo);

                emptyCondition.signalAll();
                lock.unlock();
            }
        }
    }


    public static class Consumer extends Thread {
        private Queue queue;
        private String name;
        private Integer maxSize;

        public Consumer(String name, Queue<Integer> queue, int maxSize) {
            super(name);
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while(true){
                lock.lock();
                while(queue.size() == 0){
                    try {
                        System.out.println("Queue is empty, Consumer[" + name + "] thread is waiting for Producer");
                        emptyCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[" + name + "] Consuming value : " + queue.poll());

                fullCondition.signalAll();
                lock.unlock();
            }
        }
    }
}
