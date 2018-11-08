package com.study.thread.multi003.conn012;

import java.util.Vector;

/**
 * @Description: 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 */
public class Tickets {
    public static void main(String[] args) {
        //初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap
        final Vector tickets = new Vector();

        //Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

        for (int i = 0; i < 1000; i++) {
            tickets.add("火车票"+i);
        }

        //ConcurrentModificationException 修改正在被操作的Vector 会报错
//        for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			tickets.remove(20);
//		}

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        if(tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }).start();
        }

    }
}
