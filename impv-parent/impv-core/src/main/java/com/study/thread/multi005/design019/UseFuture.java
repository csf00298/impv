package com.study.thread.multi005.design019;

import java.util.concurrent.*;

/**
 * @Description: 适合有返回值的线程去执行.
 */
public class UseFuture  implements Callable<String> {

    public String para;
    public UseFuture(String para){
        this.para = para;
    }

    /**
     * 这里是真实的业务逻辑，其执行可能很慢
     */
    @Override
    public String call() throws Exception {
        //模拟执行耗时
        Thread.sleep(5000);
        String result = para+"-线程任务执行完成";
        return result;
    }

    //主控制函数
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String taskName = "task";
        //构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
        FutureTask<String> future1 = new FutureTask<String>(new UseFuture(taskName));
        FutureTask<String> future2 = new FutureTask<String>(new UseFuture(taskName));

        //创建一个固定线程的线程池且线程数为2,
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //这里提交任务future,则开启线程执行RealData的call()方法执行
        //submit和execute的区别： 第一点是submit可以传入实现Callable接口的实例对象， 第二点是submit方法有返回值
        executor.submit(future1); //线程已经提交 并开始执行
        executor.submit(future2);
        System.out.println("线程已经提交 并开始执行");

        Thread.sleep(1000);
        //这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
        System.out.println("处理主线程中的其他实际的业务逻辑...");

        //调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
        System.out.println("数据：" + future1.get());
        System.out.println("数据：" + future2.get());

        executor.shutdown();
    }
}
