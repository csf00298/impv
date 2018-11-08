package com.demo.threadInPro.thread.queue;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/7/4.
 */
public class ProducerConsumerByQueue {

    public static void main(String[] args) {
        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<Integer>(5);
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
        private LinkedBlockingDeque<Integer> queue;
        private String name;
        private Integer maxSize;
        private Integer taskNo = 0;

        public Producer(String name, LinkedBlockingDeque <Integer> queue, int maxSize) {
            super(name);
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    queue.put( ++ taskNo);
                    System.out.println("[" + name + "] Producing value : " + taskNo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer extends Thread {
        private LinkedBlockingDeque<Integer> queue;
        private String name;
        private Integer maxSize;
        private Integer taskNo = 0;

        public Consumer(String name, LinkedBlockingDeque <Integer> queue, int maxSize) {
            super(name);
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while(true){
                try {
                    System.out.println("[" + name + "] Consuming : " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
