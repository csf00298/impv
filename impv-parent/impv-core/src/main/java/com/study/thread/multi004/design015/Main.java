package com.study.thread.multi004.design015;

import java.util.Random;

/**
 * @Description: Created by Admin on 2016/11/24.
 */
public class Main {
    public static void main(String[] args) {

        Master master = new Master(new Worker(), 20);

        Random r = new Random();
        for(int i = 1; i <= 100; i++){
            Task t = new Task();
            t.setId(i);
            t.setPrice(r.nextInt(1000));
            master.submit(t);
        }
        master.execute();
        long start = System.currentTimeMillis();

        while(true){
            if(master.isComplete()){
                long end = System.currentTimeMillis() - start;
                int priceResult = master.getResult();
                System.out.println("最终结果：" + priceResult + ", 执行时间：" + end);
                break;
            }
        }

    }
}
