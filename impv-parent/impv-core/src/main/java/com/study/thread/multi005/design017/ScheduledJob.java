package com.study.thread.multi005.design017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 每隔**秒 执行一次任务
 */

public class ScheduledJob {
    public static void main(String[] args) {
        Temp temp = new Temp();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1); //创建一个可以调度命令在一个给定的延迟后运行的线程池，
//        command - the task to execute
//        initialDelay - the time to delay first execution
//        delay - the delay between the termination of one execution and the commencement of the next
//        unit - the time unit of the initialDelay and delay parameters
        ScheduledFuture<?> scheduleTask = service.scheduleWithFixedDelay(temp,5,1, TimeUnit.SECONDS);
    }
}

class Temp extends Thread{
    public void run(){
        System.out.println("run..............");
    }
}
