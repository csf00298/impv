package com.demo.tlist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Created by Admin on 2016/10/31.
 */
public class TestSubList {
//    Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void test01() {
        List<String> adminlist = new ArrayList<>();
        int lastSubNum = 0;

        for (int i = 1; i <= 144; i++) {
            adminlist.add(String.valueOf(i));
        }
        System.out.println("old List " + adminlist.toString());

        int nThreads = 4;
        int size = adminlist.size();
        if (size > 99) nThreads = 10;
        for (int i = 0; i < nThreads+1; i++) { //对未整除的再循环一次
            int endNum = 0;
            lastSubNum = size / nThreads * (i + 1);
            if (nThreads-i == 0 && size -lastSubNum*nThreads <nThreads) {
                endNum = adminlist.size();
            } else {
                endNum = size / nThreads * (i + 1);
            }

//            System.out.println("save拆分开始位置 " + size / nThreads * i + "   " + " save拆分结束位置 " + endNum);
            List<String> childTask = adminlist.subList(size / nThreads * i, endNum);
            if(childTask.size()>0){
                System.out.println("sub List " + childTask + "   " + childTask.size());
            }
        }

    }
}
