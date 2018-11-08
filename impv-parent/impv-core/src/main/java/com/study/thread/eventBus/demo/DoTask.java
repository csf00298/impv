package com.study.thread.eventBus.demo;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.study.thread.eventBus.EventBusUtil;
import com.study.thread.eventBus.entity.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/6/4.
 */
@Slf4j
public class DoTask {
    EventBusUtil eventBusHandler = null;

    private DoTask() {
        eventBusHandler = new EventBusUtil(Runtime.getRuntime().availableProcessors() * 5,
                Lists.newArrayList(new MyTask1Executor(), new MyTask2Executor()));

    }

    public void createTask() {
        while (true) {
            int num = (int) (Math.random() * 10);
            System.out.print(num);
            eventBusHandler.send(new Person(num, "name" + num), String.valueOf(num));
        }
    }


    public class MyTask1Executor {

        @Subscribe
        public void doRun(Person person) throws InterruptedException {
            System.out.println("task1 send "+person.toString());
            Thread.sleep(1000);
        }
    }

    public class MyTask2Executor {
        @Subscribe
        public void doRun(Person person) throws InterruptedException {
            System.out.println("task2 send "+person.toString());
            Thread.sleep(1000);
        }
    }



    public static void main(String[] args) {
//        new DoTask().createTask();
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 7, 8);
        List<Object> transform = Lists.transform(integers, input -> input * 2);
        System.out.println(transform);
        List<List<Object>> partition = Lists.partition(transform, 3);
        System.out.println(partition);

    }
}
