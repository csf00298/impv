package com.study.SuanFa;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test {
    List<String> listDemo = Lists.newArrayList("a","b","c","d","e");

    @org.junit.Test
    public void test01(){
        Iterator<String> iterator = listDemo.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("b")) {
                iterator.remove();
            }
        }
    }


    @org.junit.Test
    public void testQueue(){
        QunObj qunObj = new QunObj();
        ArrayList<String> strings = Lists.newArrayList("a", "b", "c");
        for (String s: strings){
            qunObj.add(s);
        }
        System.out.println(qunObj.queue.toString());
        qunObj.peek();
        System.out.println(qunObj.queue.toString());
        qunObj.pop();
        System.out.println(qunObj.queue.toString());
    }

    public class QunObj<T>{
        LinkedList<T> queue = new LinkedList<>();
        public synchronized void add(T str) {
            queue.add(str);
        }

        public T pop(){
            return queue.removeFirst();
        }

        public T peek(){
            return queue.peek();
        }
    }
}
