package com.study.thread.multi003.conn013;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description: 适用于高并发读取 读多写少
 */
public class UseCopyOnWrite {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> cow1 = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> cow2 = new CopyOnWriteArraySet<>();
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(5);//有界队列
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();//无界队列
    }
}
