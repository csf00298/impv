package com.study.thread.multi002.conn009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: Created by Admin on 2016/11/10.
 */
public class MyQueue {
    //1 需要一个承装元素的集合
    private volatile LinkedList<Object> queue = new LinkedList<Object>();
    //2 需要一个计数器
    private AtomicInteger count = new AtomicInteger(0);
    //5 初始化一个对象 用于加锁
    private Object lock = new Object();
    //3 需要制定上限和下限
    private int maxNum ;
    private int minNum = 0;
    //4 构造方法
    MyQueue(int maxNum){
        this.maxNum = maxNum;
    }


    //put(anObject): 把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.
    public void put(Object obj){
        synchronized (lock){
            while(count.get() == this.maxNum){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //1 加入元素
            queue.add(obj);
            //2 计数器累加
            count.incrementAndGet();
            //3 通知另外一个线程（唤醒）
            lock.notify();
            System.out.println("新加入的元素为:" + obj);
        }
    }

    //take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入.
    public Object take(){
        synchronized (lock){
            while (count.get() == this.minNum){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 做移除元素操作
            Object firstDoc = queue.removeFirst();
            // 唤醒另外一个线程
            lock.notify();
            // 计数器递减
            count.decrementAndGet();
            return  firstDoc;
        }
    }

    public static void main(String[] args) {
        final MyQueue demo = new MyQueue(5);
        demo.put("a");
        demo.put("b");
        demo.put("c");
        demo.put("d");
        demo.put("e");

        System.out.println("当前容器的长度----:" + demo.queue.size());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.put("f");
                demo.put("g");
            }
        },"t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = demo.take();
                System.out.println("移除的元素为:" + o1);
                Object o2 = demo.take();
                System.out.println("移除的元素为:" + o2);
            }
        },"t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}


