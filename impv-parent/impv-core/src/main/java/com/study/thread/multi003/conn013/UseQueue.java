package com.study.thread.multi003.conn013;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: Created by Admin on 2016/11/11.
 */
public class UseQueue {
    public static void main(String[] args) throws Exception {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.add("e");

        System.out.println(queue.peek());//a
        System.out.println(queue.size());//5
        System.out.println(queue.poll());//a 从头部取出元素，并从队列里删除
        System.out.println(queue.size());//4

        ArrayBlockingQueue<String> array = new ArrayBlockingQueue(6);
        array.put("a");
        array.put("b");
        array.put("c");
        array.add("d");
        array.add("e");
        System.out.println(array.offer("a", 3, TimeUnit.SECONDS));//3秒没有


        //阻塞队列
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>();
        q.offer("a");
        q.offer("b");
        q.offer("c");
        q.offer("d");
        q.offer("e");
        q.add("f");
        System.out.println(q.size());

        for (Iterator iterator = q.iterator(); iterator.hasNext(); ) {
            String string = (String) iterator.next();
            System.out.println(string);
        }

        List<String> list = new ArrayList<String>();
        System.out.println(q.drainTo(list, 4));//从队列q中截取前3个元素 放入list集合中
        System.out.println(list.size());
        for (String string : list) {
            System.out.println(string);
        }


        final SynchronousQueue<String> sq = new SynchronousQueue<String>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(sq.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                sq.add("asdasd");
            }
        });
        t2.start();
    }

}
