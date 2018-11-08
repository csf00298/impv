package com.demo.threadInPro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: Created by Admin on 2016/11/1.
 */
public class ThreadUtil {
    //volatile修饰符 保证变量的可见性 即子线程从主内存中取到的值是最新的，但是并不能保证对变量的操作的原子性。
    private static volatile ExecutorService executor;

    static {
        initExecutor();
    }

    //初始化线程池
    private static void initExecutor() {
        if (executor == null) {
            synchronized (ThreadUtil.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(200, 200, 1,
                            TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), new DefaultThreadFactory());
                    //停止监控
//                    executor.submit(new Monitor((ThreadPoolExecutor) executor));
                }
            }
        }
    }

    /**
     * 异步执行一个任务
     * @param task 任务
     */
    public static Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    /**
     * 异步执行一个任务
     * @param task 任务
     * @return 任务结果
     */
    public static <T> Future<T> submit(Callable<T> task){
        return executor.submit(task);
    }

    /**
     * 异步执行，超时会停止任务
     * @param timeout 超时时间为秒
     */
    public static <T> T submit(Callable<T> task, int timeout) {
        Future<T> future = executor.submit(task);
        T t;
        try {
            t = future.get(timeout, TimeUnit.SECONDS);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            future.cancel(true);
            task = null;
            t = null;
        }
        return null;
    }

    /**
     * 批量提交任务 返回结果
     */
    public static <V> List<V> submit(List<Callable<V>> callables) {
        List<Future<V>> futures = new ArrayList<Future<V>>();
        List<V> result = new ArrayList<>();
        try {
            futures = executor.invokeAll(callables);
            for (Future<V> future : futures) {
                try {
                    V v = future.get();
                    if (v != null)
                        result.add(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量执行任务
     * 超时间为秒
     */
    public static <V> List<V> submit(List<Callable<V>> callables, long timeout) {
        List<Future<V>> futures = new ArrayList<>();
        List<V> result = new ArrayList<>();
        try {
            futures = executor.invokeAll(callables, timeout, TimeUnit.SECONDS);
            for (Future<V> future : futures) {
                try {
                    V v = future.get();
                    if (v != null)
                        result.add(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 关闭服务
     */
    public static void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    /**
     * 线程工厂
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private ThreadGroup threadGroup = null;
        private String namePrefix = null;

        public DefaultThreadFactory() {
            SecurityManager sm = System.getSecurityManager();
            this.threadGroup = sm != null ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + DefaultThreadFactory.poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.threadGroup, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())//是否是守护线程
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static class Monitor implements Runnable {
        final ThreadPoolExecutor executor;

        Monitor(ThreadPoolExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            while (!this.executor.isShutdown()) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
