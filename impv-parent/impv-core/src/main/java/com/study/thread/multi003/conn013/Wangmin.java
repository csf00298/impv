package com.study.thread.multi003.conn013;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Created by Admin on 2016/11/21.
 */
public class Wangmin implements Delayed{
    private String name;
    //身份证
    private String id;
    //截止时间
    private long endTime;
    //定义时间工具类
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public Wangmin(String name, String id, long endTime) {
        this.name = name;
        this.id = id;
        this.endTime = endTime;
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    /**
     * 用来判断是否到了截止时间
     */
    @Override
    public long getDelay(TimeUnit unit) {

        return endTime - System.currentTimeMillis();
    }

    /**
     * 相互比较 用于排序
     */
    @Override
    public int compareTo(Delayed delayed) {
        Wangmin w = (Wangmin) delayed;
        return this.getDelay(this.timeUnit) - w.getDelay(this.timeUnit)>0?1:0;
    }
}
