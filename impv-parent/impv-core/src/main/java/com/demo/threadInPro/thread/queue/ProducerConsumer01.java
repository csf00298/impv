package com.demo.threadInPro.thread.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/7/4.
 */
public class ProducerConsumer01 {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
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
        private Queue<Integer> queue;
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
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out .println("Queue is full, Producer[" + name + "] thread waiting for " + "consumer to take something from queue.");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(++taskNo);
                    queue.notifyAll();
                }
            }
        }
    }

    public static class Consumer extends Thread {
        private Queue<Integer> queue;
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
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("Queue is empty, Consumer[" + name + "] thread is waiting for Producer");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("do task "+ queue.poll());
                    queue.notifyAll();
                }
            }
        }
    }

}
