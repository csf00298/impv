package com.study.thread.multi003.conn013;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Description: Created by Admin on 2016/11/11.
 */
public class UsePriorityBlockingQueue {

    public static void main(String[] args) throws Exception{
        //Task对象需实现compare接口 以此实现比较功能
        PriorityBlockingQueue<Task> q = new PriorityBlockingQueue();

        Task t1 = new Task();
        t1.setId(3);
        t1.setName("id为3");
        Task t2 = new Task();
        t2.setId(4);
        t2.setName("id为4");
        Task t3 = new Task();
        t3.setId(1);
        t3.setName("id为1");

        q.add(t1);//只向队列中添加元素 但是不会立即比较，只有在调用take()时才会比较
        q.add(t2);
        q.add(t3);

        System.out.println("容器：" + q);
        System.out.println(q.take().getId());
        System.out.println("容器：" + q);
    }
}
