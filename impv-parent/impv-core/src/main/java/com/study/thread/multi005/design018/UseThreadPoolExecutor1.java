package com.study.thread.multi005.design018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Created by Admin on 2016/11/28.
 */
public class UseThreadPoolExecutor1 {

    /**
     * 运行结果: 线程1先执行，2-4被放入有界队列，线程5直接被执行 之后执行队列中的2-4
     */
    public static void main(String[] args) {
        /**
         * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
         * 若大于corePoolSize，则会将任务加入队列，
         * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
         * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 				//coreSize
                2, 				//MaxSize
                60, 			//60
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)			//指定一种队列 （有界队列）
//                new LinkedBlockingQueue<Runnable>()
                , new MyRejected() //实现了RejectedExecutionHandler接口的拒绝方法
                //, new DiscardOldestPolicy()
        );

        MyTask task1 = new MyTask(1,"Thread1");
        MyTask task2 = new MyTask(2,"Thread2");
        MyTask task3 = new MyTask(3,"Thread3");
        MyTask task4 = new MyTask(4,"Thread4");
        MyTask task5 = new MyTask(5,"Thread5");

        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.execute(task4);
        executor.execute(task5);

        executor.shutdown();
    }
}
