package com.study.thread.multi005.design018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: Created by Admin on 2016/11/28.
 */
public class MyRejected implements RejectedExecutionHandler {


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义处理..");
        System.out.println("当前被拒绝任务为：" + r.toString());
    }
}
