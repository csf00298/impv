package com.study.thread.multi003.conn013;

import java.util.concurrent.DelayQueue;

/**
 * @Description: Created by Admin on 2016/11/21.
 */
public class WangBa implements Runnable{
    private DelayQueue<Wangmin> queue = new DelayQueue<>();
    public boolean yingYe = true;

    public void shangji(String name, String id, int money) {
        Wangmin man = new Wangmin(name, id, 1000 * money + System.currentTimeMillis());
        System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "交钱" + money + "块,开始上机...");
        this.queue.add(man);
    }

    public void xiaji(Wangmin man){
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"时间到下机...");
    }

    @Override
    public void run(){
        while (yingYe){
            try {
                Wangmin man = queue.take();
                xiaji(man);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("网吧开始营业");
        WangBa siyu = new WangBa();
        Thread shangwang = new Thread(siyu);
        shangwang.start();

        siyu.shangji("路人甲", "123", 1);
        siyu.shangji("路人乙", "234", 10);
        siyu.shangji("路人丙", "345", 5);
    }

}

